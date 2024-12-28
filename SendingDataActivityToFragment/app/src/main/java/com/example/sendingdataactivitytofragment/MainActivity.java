package com.example.sendingdataactivitytofragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    EditText weight,height;
    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        MyFirstFragment myFirstFragment=new MyFirstFragment();

        weight=findViewById(R.id.editTextWeight);
        height=findViewById(R.id.editTextHeight);
        calculate=findViewById(R.id.buttonCalculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                int userWeight= Integer.parseInt(weight.getText().toString());
                int userHeight= Integer.parseInt(height.getText().toString());

                bundle.putInt("weight",userWeight);
                bundle.putInt("height",userHeight);

                myFirstFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.frame,myFirstFragment);
                fragmentTransaction.commit();
            }
        });

    }
}