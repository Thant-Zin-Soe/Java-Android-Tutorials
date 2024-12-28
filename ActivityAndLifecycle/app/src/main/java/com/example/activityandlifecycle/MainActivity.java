package com.example.activityandlifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button addOneBtn;
    Button chgActivityBtn;
    int counter=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.d("Message","onCreate");

        textView=findViewById(R.id.textView);
        addOneBtn=findViewById(R.id.plusOneBtn);
        chgActivityBtn=findViewById(R.id.chgActivityBtn);

        addOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter+=1;
                textView.setText(""+counter);
            }
        });

        chgActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Message","onDestory");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Message","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Message","onStop");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Message","onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Message","onResume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Message","onrestart");

    }
}