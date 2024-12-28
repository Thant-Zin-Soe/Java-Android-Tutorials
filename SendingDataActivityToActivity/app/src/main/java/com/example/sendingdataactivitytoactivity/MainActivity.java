package com.example.sendingdataactivitytoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name,email,phoneNumber;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editTextName);
        email=findViewById(R.id.editTextEmail);
        phoneNumber=findViewById(R.id.editTextPhoneNumber);
        signUpBtn=findViewById(R.id.signUpButton);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();
                String userEmail=email.getText().toString();
                int userPhoneNumber= Integer.valueOf(phoneNumber.getText().toString());

                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("n",userName);
                i.putExtra("m",userEmail);
                i.putExtra("p",userPhoneNumber);
                startActivity(i);
            }
        });

    }
}