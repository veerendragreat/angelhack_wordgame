package com.example.home.wordgame;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.wordgame.fragments.MainActivityFragment;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.repository.DaoOperations;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateWords extends Fragment {


    public CreateWords() {
        // Required empty public constructor
    }

//    android:textSize="18sp"
  //  tools:layout_width="220dp"


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Log.d("Android : ", "Entered Create Words Fragment");
        View view = inflater.inflate(R.layout.fragment_create_words, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Storing Words Please wait...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                storeData(inflater,container);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new MainActivityFragment(), "fragment").commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    private void storeData(LayoutInflater inflater,  ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_create_words, container, true);


        //view.findViewById(getResources().getIdentifier(VIEW_NAME, "id", getPackageName()));
        EditText word1= (EditText)view.findViewById(R.id.Word1);
        EditText word2= (EditText)view.findViewById(R.id.Word2);
        EditText word3= (EditText)view.findViewById(R.id.Word3);
        EditText word4= (EditText)view.findViewById(R.id.Word4);
        EditText word5= (EditText)view.findViewById(R.id.Word5);
        //String str=word1.getText().toString();
        //Log.d("Android : ", str);

        EditText meaning1= (EditText)view.findViewById(R.id.Meaning1);
        EditText meaning2= (EditText)view.findViewById(R.id.Meaning2);
        EditText meaning3= (EditText)view.findViewById(R.id.Meaning3);
        EditText meaning4= (EditText)view.findViewById(R.id.Meaning4);
        EditText meaning5= (EditText)view.findViewById(R.id.Meaning5);
        DaoOperations daoOperations = new DaoOperations(getContext());
        daoOperations.open();
        Words objWord1 = new Words(word1.getText().toString(), meaning1.getText().toString());
        daoOperations.addWords(objWord1);
        Words objWord2 = new Words(word2.getText().toString(), meaning2.getText().toString());
        daoOperations.addWords(objWord2);
        Words objWord3 = new Words(word3.getText().toString(), meaning3.getText().toString());
        daoOperations.addWords(objWord3);
        Words objWord4 = new Words(word4.getText().toString(), meaning4.getText().toString());
        daoOperations.addWords(objWord4);
        Words objWord5 = new Words(word5.getText().toString(), meaning5.getText().toString());
        daoOperations.addWords(objWord5);
        Toast t = Toast.makeText(getContext(), "added successfully !", Toast.LENGTH_SHORT);
        t.show();
        daoOperations.close();
        }

}
