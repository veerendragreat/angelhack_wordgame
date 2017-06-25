package com.example.home.wordgame.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.home.wordgame.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
        Log.d("Android : ", "Entered into MainActivityFragment constructor.");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState) {
        Log.d("Android : ", "Entered into MainActivityFragment onCreateView method.");
        return inflater.inflate(R.layout.fragment_main_list, container, false);

    }

    private void showFragment(Fragment fragment, int id) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(id, fragment, "fragment").commit();
    }

}
