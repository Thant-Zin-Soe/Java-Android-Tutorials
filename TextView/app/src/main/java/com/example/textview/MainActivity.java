package com.example.textview;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

         text= findViewById(R.id.textViewId);
         text.setText("Hello Thant Zin Soe");
         text.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 text.setTextColor(Color.CYAN);

             }
         });
    }


}