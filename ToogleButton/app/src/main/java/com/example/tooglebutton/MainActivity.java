package com.example.tooglebutton;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ToggleButton toogleBtn;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        toogleBtn=findViewById(R.id.toggleButton);
        image=findViewById(R.id.imageView);

      toogleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(toogleBtn.isChecked()){
                  image.setVisibility(ImageView.INVISIBLE);
              }
              else {
                  image.setVisibility(ImageView.VISIBLE);
              }
          }
      });
    }
}