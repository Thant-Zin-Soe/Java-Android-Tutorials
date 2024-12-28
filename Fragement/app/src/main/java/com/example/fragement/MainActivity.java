package com.example.fragement;

import android.os.Bundle;
import android.util.Log;

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
        Log.d("Message","First Activity onCreate");

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