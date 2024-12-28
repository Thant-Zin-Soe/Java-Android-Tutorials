package com.example.activityandlifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    Button goToActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        goToActivity=findViewById(R.id.goToActivityBtn);

        goToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        Log.d("Message","SecondActivity onCreate");

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