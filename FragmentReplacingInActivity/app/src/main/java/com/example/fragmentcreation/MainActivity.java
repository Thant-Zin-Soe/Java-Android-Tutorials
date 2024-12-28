package com.example.fragmentcreation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Button replace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        FirstFragment firstFragment=new FirstFragment();
        fragmentTransaction.add(R.id.frame,firstFragment);

        fragmentTransaction.commit();

        replace= findViewById(R.id.buttonReplace);

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager1=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();

                SecondFragment secondFragment=new SecondFragment();
                fragmentTransaction1.replace(R.id.frame,secondFragment);

                fragmentTransaction1.addToBackStack(null);//adding to stack as much as i want (many fragments can add)

                fragmentTransaction1.commit();
            }
        });

    }
}