package com.example.checkbox;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView result;
    CheckBox male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

            result =findViewById(R.id.resultTextView);
            male=findViewById(R.id.maleCheckBox);
            female=findViewById(R.id.femaleCheckBox);

            male.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(male.isChecked()){
                        result.setText("You are Male");
                        female.setChecked(false);
                    }
                    else {
                        result.setText("");
                    }
                }
            });

            female.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(female.isChecked()){
                        result.setText("You are Female");
                        male.setChecked(false);
                    }
                    else{
                        result.setText("");
                    }
                }
            });

    }

}