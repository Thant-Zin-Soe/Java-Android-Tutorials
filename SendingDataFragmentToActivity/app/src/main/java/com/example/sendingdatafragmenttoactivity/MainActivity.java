package com.example.sendingdatafragmenttoactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        MyFirstFragment myFirstFragment=new MyFirstFragment();
        fragmentTransaction.add(R.id.linear,myFirstFragment);
        fragmentTransaction.commit();



    }

    public  void takeData(String userName,String userEmail){

        name=findViewById(R.id.textViewName);
        email=findViewById(R.id.textViewEmail);
        name.setText(userName);
        email.setText(userEmail);
    }
}