package com.example.myfirstapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void disableBtn(View v){
//        v.setEnabled(false);
//        Button disableBtn= (Button) v;
//        disableBtn.setText("Disable");

        View disableBtn=findViewById (R.id.disableBtnId);
        disableBtn.setEnabled(false);
        ((Button) findViewById(R.id.disableBtnId)).setText("New cast TextDisable");

//        Button button = (Button) disableBtn;Simple casting
//        button.setText("New Text Disable");


    }
}