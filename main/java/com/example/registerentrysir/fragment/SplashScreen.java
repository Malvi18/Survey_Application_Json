package com.example.registerentrysir.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.registerentrysir.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreen extends Fragment {


    public SplashScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_splash_screen, container, false);


        return view;
    }

}
