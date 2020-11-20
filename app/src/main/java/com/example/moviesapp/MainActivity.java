package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.moviesapp.utils.Utils;

public class MainActivity extends AppCompatActivity  {


    //fragment transaction
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            ListMovieFragment listMovieFragment = ListMovieFragment.newInstance();
            Utils.setFragment(getSupportFragmentManager(), listMovieFragment);
        }

    }



}

