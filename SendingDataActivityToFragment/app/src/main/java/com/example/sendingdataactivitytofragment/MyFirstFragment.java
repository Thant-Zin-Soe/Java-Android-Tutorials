package com.example.sendingdataactivitytofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFirstFragment extends Fragment {

    TextView result;

    public MyFirstFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                    View view=inflater.inflate(R.layout.fragment_my_first,container,false);
                    result=view.findViewById(R.id.textViewResult);

                    Bundle bundle=getArguments();
                    int userWeight=bundle.getInt("weight");
                    int userHeight=-bundle.getInt("height");

                    double userBMI=(userWeight*1000)/(userHeight*userWeight);//formula and culculate BMI

                    result.setText("Your BMI is : "+userBMI);

                    return  view;
         }
}