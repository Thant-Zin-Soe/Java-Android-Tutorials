package com.example.takenote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText description;
    EditText title;
    Button save;
    Button cancel;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);



        title = findViewById(R.id.editTextTitleUpdate);
        description = findViewById(R.id.editTextDescriptionUpdate);
        cancel = findViewById(R.id.cancelButtonUpdate);
        save = findViewById(R.id.saveButtonUpdate);


        getData();///////Note this one



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(),"Noithing Update",Toast.LENGTH_LONG).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });
    }

    private void updateNote(){////sent tot the database

        String titleLast=title.getText().toString();
        String descriptionLast=description.getText().toString();

        Intent intent=new Intent();
        intent.putExtra("titleLast",titleLast);
        intent.putExtra("descriptionLast",descriptionLast);

        if(noteId !=-1){
            intent.putExtra("noteId",noteId);
            setResult(RESULT_OK,intent);
            finish();
        }



    }

    public  void getData(){
        Intent i=getIntent();
         noteId=i.getIntExtra("id",-1);
       String noteTitle= i.getStringExtra("title");
        String noteDescription=i.getStringExtra("description");
        title.setText(noteTitle);
        description.setText(noteDescription);


    }
}