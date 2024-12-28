package com.example.listfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);


        Intent intent=getIntent();
        int position=intent.getIntExtra("position",0);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        SecondFragment secondFragment=new SecondFragment();

        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        secondFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.frameLayout,secondFragment);

        fragmentTransaction.commit();

    }
}