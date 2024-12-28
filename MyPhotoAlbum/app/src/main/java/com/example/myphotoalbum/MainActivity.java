package com.example.myphotoalbum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyImagesViewModel myImagesViewModel;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private  MyImagesAdapter myImagesAdapter;

    private ActivityResultLauncher<Intent> activityResultLauncherForAddImage;
    private ActivityResultLauncher<Intent> activityResultLauncherForUpdateImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv=findViewById(R.id.recyclerView);
        fab=findViewById(R.id.floatingActionButton);
        //register
        registerActivityForAddImage();
        registerActivityForUpdateImage();

        myImagesAdapter=new MyImagesAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myImagesAdapter);

        myImagesViewModel =new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);

        myImagesViewModel.getAllImages().observe(MainActivity.this, new Observer<List<MyImages>>() {
            @Override
            public void onChanged(List<MyImages> myImages) {

                myImagesAdapter.setImagesList(myImages);

            }
        });

//        myImagesViewModel.getAllImages().observe(this, images -> {
//            if (images != null) {
//                myImagesAdapter.setImagesList(images); // Update adapter
//            } else {
//                Log.d("MainActivity", "No images found.");
//            }
//        });


        fab.setOnClickListener(v->{
            Toast.makeText(this, "FAB Clicked", Toast.LENGTH_SHORT).show();
            Log.d("FAB", "FloatingActionButton clicked!");
            Intent intent=new Intent(MainActivity.this,AddImageActivity.class);
            startActivity(intent);

        });
//
//        fab.setOnClickListener(v -> {
//            Toast.makeText(this, "FAB Clicked", Toast.LENGTH_SHORT).show();
//            Log.d("FAB", "FloatingActionButton clicked!");
//
//            finish(); // End the current activity and return to MainActivity
//        });


//        fab.setOnClickListener(v -> {
//            try {
//                Log.d("FAB", "FloatingActionButton clicked!");
//                Intent intent = new Intent(MainActivity.this, AddImageActivity.class);
//                activityResultLauncherForAddImage.launch(intent);
//
//
//
//            } catch (Exception e) {
//                Log.e("FAB", "Error launching AddImageActivity", e);
//            }
//        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT  | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                myImagesViewModel.delete(myImagesAdapter.getPosition(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(rv);

        myImagesAdapter.setListener(new MyImagesAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(MyImages myImages) {
                Intent intent=new Intent(MainActivity.this, UpdateImageActivity.class);
                intent.putExtra("id",myImages.getImage_id());
                intent.putExtra("title",myImages.getImage_title());
                intent.putExtra("description",myImages.getImage_description());
                intent.putExtra("image",myImages.getImage());

                //start activity with activityResultLauncher

                activityResultLauncherForUpdateImage.launch(intent);
            }
        });

    }






    public void registerActivityForUpdateImage(){

        activityResultLauncherForUpdateImage=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                int resultCode= result.getResultCode();
                Intent data=result.getData();

                if(resultCode==RESULT_OK && data!=null){
                    String title=data.getStringExtra("updateTitle");
                    String description=data.getStringExtra("updateDescription");
                    byte[] image=data.getByteArrayExtra("image");
                    int id=data.getIntExtra("id",-1);

                    MyImages myImages=new MyImages(image,description,title);
                    myImages.setImage_id(id);

                    myImagesViewModel.update(myImages);
                }


            }
        });

    }
    public void registerActivityForAddImage() {
        activityResultLauncherForAddImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    int resultCode = result.getResultCode();
                    Intent data = result.getData();

                    if (resultCode == RESULT_OK && data != null) {
                        String title = data.getStringExtra("title");
                        String description = data.getStringExtra("description");
                        byte[] image = data.getByteArrayExtra("image");

                        if (title != null && description != null && image != null) {
                            MyImages myImages = new MyImages(image, description, title);
                            myImagesViewModel.insert(myImages); // Insert into ViewModel
                        } else {
                            Toast.makeText(this, "Invalid data received!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("MainActivity", "No data received or result not OK or some data fields are missing");
                    }
                }
        );
    }

}








