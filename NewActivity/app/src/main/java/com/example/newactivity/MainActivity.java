package com.example.newactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
        public void settingOnClick(View v){


            Intent i= new Intent(this,Setting.class);
            String name= ((TextView)findViewById(R.id.editTextText)).getText().toString();

            i.putExtra("COOL",name);
            startActivity(i);

            //Log.d("info","Button works well");
    }
}