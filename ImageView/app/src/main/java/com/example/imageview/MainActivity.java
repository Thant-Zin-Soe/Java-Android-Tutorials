package com.example.imageview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button chgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        image=findViewById(R.id.imageView);
        chgBtn=findViewById(R.id.chgBtn);

        image.setImageResource(R.drawable.picture1);
        image.setTag("picture1");

        chgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the current tag and toggle the image
                if ("picture1".equals(image.getTag())) {
                    image.setImageResource(R.drawable.picture2);
                    image.setTag("picture2");
                } else {
                    image.setImageResource(R.drawable.picture1);
                    image.setTag("picture1");
                }
            }
        });
    }
}