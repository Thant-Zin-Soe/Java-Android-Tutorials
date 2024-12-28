package com.example.sendingdataactivitytoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    TextView na,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second3);

        na=findViewById(R.id.textViewName);
        email=findViewById(R.id.textViewEmail);
        phone=findViewById(R.id.textViewPhoneNumber);

        Intent i=getIntent();
        String userName=i.getStringExtra("n");
        String userEmail=i.getStringExtra("m");
        int userPhone=i.getIntExtra("p",0);

         na.setText(userName);
        email.setText(userEmail);
        phone.setText(String.valueOf(userPhone));


    }
}