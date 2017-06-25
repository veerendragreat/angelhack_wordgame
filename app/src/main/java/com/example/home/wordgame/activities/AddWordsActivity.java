package com.example.home.wordgame.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.wordgame.R;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.fragments.MainActivityFragment;
import com.example.home.wordgame.fragments.WordsListFragment;
import com.example.home.wordgame.repository.DaoOperations;

public class AddWordsActivity extends AppCompatActivity {
    public AddWordsActivity() {
        Log.d("Android : ", "Entered AddWordsActivity constructor.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);
        Log.d("Android : ", "Entered AddWordsActivity onCreateMethod");
        FloatingActionButton btAddWords = (FloatingActionButton) findViewById(R.id.bt_add_words);
        btAddWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Android : ", "Entered AddWordsActivity Add Words button clicked.");
                Snackbar.make(view, "Storing Words Please wait...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                storeData();
                Intent myIntent = new Intent(AddWordsActivity.this, MainActivity.class);
                AddWordsActivity.this.startActivity(myIntent);
            }
        });

    }

    private void storeData() {
        //view.findViewById(getResources().getIdentifier(VIEW_NAME, "id", getPackageName()));
        EditText word1 = (EditText) findViewById(R.id.Word1);
        EditText word2 = (EditText) findViewById(R.id.Word2);
        EditText word3 = (EditText) findViewById(R.id.Word3);
        EditText word4 = (EditText) findViewById(R.id.Word4);
        EditText word5 = (EditText) findViewById(R.id.Word5);
        //String str=word1.getText().toString();
        //Log.d("Android : ", str);

//Added for commit

        EditText meaning1 = (EditText) findViewById(R.id.Meaning1);
        EditText meaning2 = (EditText) findViewById(R.id.Meaning2);
        EditText meaning3 = (EditText) findViewById(R.id.Meaning3);
        EditText meaning4 = (EditText) findViewById(R.id.Meaning4);
        EditText meaning5 = (EditText) findViewById(R.id.Meaning5);
        DaoOperations daoOperations = new DaoOperations(this);
        daoOperations.open();
        if (!word1.getText().toString().isEmpty()) {
            Words objWord1 = new Words(word1.getText().toString(), meaning1.getText().toString());
            daoOperations.addWords(objWord1);
        }
        if (!word2.getText().toString().isEmpty()) {

            Words objWord2 = new Words(word2.getText().toString(), meaning2.getText().toString());
            daoOperations.addWords(objWord2);
        }
        if (!word3.getText().toString().isEmpty()) {

            Words objWord3 = new Words(word3.getText().toString(), meaning3.getText().toString());
            daoOperations.addWords(objWord3);
        }
        if (!word4.getText().toString().isEmpty()) {

            Words objWord4 = new Words(word4.getText().toString(), meaning4.getText().toString());
            daoOperations.addWords(objWord4);
        }
        if (!word5.getText().toString().isEmpty()) {
            Words objWord5 = new Words(word5.getText().toString(), meaning5.getText().toString());
            daoOperations.addWords(objWord5);
        }
        Toast t = Toast.makeText(this, "Words are added successfully !", Toast.LENGTH_SHORT);
        t.show();
        daoOperations.close();
    }
}
