package com.example.takenote;

import static com.example.takenote.R.id.*;
import com.example.takenote.R;
import com.google.android.material.appbar.MaterialToolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    ActivityResultLauncher<Intent> activityResultLauncherForAddNote;
    ActivityResultLauncher<Intent> activityResultLauncherForUpdateNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        Log.d("TestLog", "App started successfully!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //to register activity
        registerActivityForAddNote();//////////////////tale note this one
        registerActivityForUpdateNote();////////////////////take note this one


        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter adapter=new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel =new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);



        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
           @Override
           public void onChanged(List<Note> notes) {

               //Update RecyclerView

               adapter.setNotes(notes);

           }
       });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {// not using drag na drop "onMove" so insert o parameter
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
              noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
              Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent=new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id",note.getId());
                intent.putExtra("title",note.getTitle());
                intent.putExtra("description",note.getDescription());

                //activitiesResultLuncher

                activityResultLauncherForUpdateNote.launch(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MainActivity", "Menu created");
        Toast.makeText(this, "Menu created", Toast.LENGTH_SHORT).show();



        getMenuInflater().inflate(R.menu.new_menu,menu);

        return  true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("MainActivity", "Menu item selected: " + item.getItemId());
        Toast.makeText(this, "Menu created", Toast.LENGTH_SHORT).show();


        if (item.getItemId() == R.id.new_menu) {
            Log.d("MenuItemID", "Top menu clicked!");
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            //startActivityForResult(intent,1);
            activityResultLauncherForAddNote.launch(intent);
            //activityResultLuncher
            return true;
        }


        return super.onOptionsItemSelected(item);



//        switch (item.getItemId()){
//            case R.id.new_menu:
//
//            Intent intent=new Intent(MainActivity.this,AddNoteActivity.class);
//            startActivity(intent);
//            return true;
//
//            default: return super.onOptionsItemSelected(item);
//        }
    }

    public void registerActivityForUpdateNote(){
        activityResultLauncherForUpdateNote=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                int resultCode=result.getResultCode();
                Intent data=result.getData();
                if(resultCode==RESULT_OK && data!=null){
                    String title=data.getStringExtra("titleLast");
                    String description=data.getStringExtra("descriptionLast");
                    int id=data.getIntExtra("noteId",-1);

                    Note note=new Note(title,description);
                    note.setId(id);
                    noteViewModel.update(note);
                }


            }
        });
    }
    public  void registerActivityForAddNote(){
        activityResultLauncherForAddNote =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int resultCode=result.getResultCode();
                Intent data=result.getData();

                if(resultCode==RESULT_OK && data!=null){
                    String title=data.getStringExtra("noteTitle");
                    String description=data.getStringExtra("noteDescription");

                    Note note=new Note(title,description);
                    noteViewModel.insert(note);
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK&& data !=null){

            String title=data.getStringExtra("noteTitle");
            String description=data.getStringExtra("noteDescription");

            Note note=new Note(title,description);
            noteViewModel.insert(note);


        }
    }
}