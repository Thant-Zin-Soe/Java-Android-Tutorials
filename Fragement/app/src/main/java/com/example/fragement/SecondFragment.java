package com.example.fragement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("fragment","onAttach Method Works");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragment","onCreate Method Works");
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("fragment","onCreated View Method Works");
        return inflater.inflate(R.layout.second_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("fragment","onViewCreated Method Works");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("fragment","onStart Method Works");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("fragment","onResume Method Works");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("fragment","onPause Method Works");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("fragment","onStop Method Works");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("fragment","onDestroyView Method Works");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("fragment","onDestroy Method Works");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("fragment","onDetach Method Works");
    }
}

