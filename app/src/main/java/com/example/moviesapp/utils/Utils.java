package com.example.moviesapp.utils;

import android.nfc.Tag;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moviesapp.R;
import com.example.moviesapp.data.Movie;
import com.example.moviesapp.data.Trailer;

public class Utils {

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }




}
