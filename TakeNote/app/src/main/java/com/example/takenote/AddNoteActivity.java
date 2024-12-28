package com.example.takenote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddNoteActivity extends AppCompatActivity {
    EditText  description;
    EditText title;
    Button save;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.editTextTitle);
        description = findViewById(R.id.editTextDescription);
        cancel = findViewById(R.id.cancelButton);
        save = findViewById(R.id.saveButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(),"Noithing saved",Toast.LENGTH_LONG).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });


    }

    public void saveNote(){

        String noteTitle=title.getText().toString();
        String noteDescription=description.getText().toString();

        Intent i=new Intent();
        i.putExtra("noteTitle",noteTitle);
        i.putExtra("noteDescription",noteDescription);
        setResult(RESULT_OK,i);
        finish();

    }
}