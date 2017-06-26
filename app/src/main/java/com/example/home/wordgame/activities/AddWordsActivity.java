package com.example.home.wordgame.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.wordgame.R;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.fragments.MainActivityFragment;
import com.example.home.wordgame.fragments.WordsListFragment;
import com.example.home.wordgame.repository.DaoOperations;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

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

        final EditText word1 = (EditText) findViewById(R.id.Word1);
        word1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                launchAynchThreadOnEnterEvent(keyCode, event, word1, R.id.Meaning1);
                return false;
            }
        });

        final EditText word2 = (EditText) findViewById(R.id.Word2);
        word2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                launchAynchThreadOnEnterEvent(keyCode, event, word2, R.id.Meaning2);
                return false;
            }
        });
        final EditText word3 = (EditText) findViewById(R.id.Word3);
        word3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                launchAynchThreadOnEnterEvent(keyCode, event, word3, R.id.Meaning3);
                return false;
            }
        });
        final EditText word4 = (EditText) findViewById(R.id.Word4);
        word4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                launchAynchThreadOnEnterEvent(keyCode, event, word4, R.id.Meaning4);
                return false;
            }
        });
        final EditText word5 = (EditText) findViewById(R.id.Word5);
        word5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                launchAynchThreadOnEnterEvent(keyCode, event, word5, R.id.Meaning5);
                return false;
            }
        });

    }

    private void launchAynchThreadOnEnterEvent(int keyCode, KeyEvent event, EditText word, int meaningId) {
        // If the event is a key-down event on the "enter" button
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            EditText meaning = (EditText) findViewById(meaningId);
            new ExecuteTask(meaning, false).execute(word.getText().toString());
            // Perform action on key press
            Toast t = Toast.makeText(AddWordsActivity.this, "Word added successfully !", Toast.LENGTH_SHORT);
            t.show();


        }

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

    class ExecuteTask extends AsyncTask<String, Integer, String> {
        EditText etView;
        boolean isRequiredSave = false;

        public ExecuteTask(EditText word, boolean isRequiredSave) {
            this.etView = word;
            this.isRequiredSave = isRequiredSave;
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected String doInBackground(String... params) {
            Log.d("Android : ", "doInbackground");
            String res = getMeaning(params);

            try {
                Gson map = new Gson();
                if (res != null || res != "") {
                    Entry_list data = map.fromJson(res, Entry_list.class);
                    List<Results> resultsList = data.getResults();
                    List<LexicalEntries> lexicalEntriesList = resultsList.get(0).getLexicalEntries();
                    List<String> definitionsList = new ArrayList<String>();
                    for (LexicalEntries lexicalEntries : lexicalEntriesList) {
                        List<Entries> entriesList = lexicalEntries.getEntries();
                        for (Entries entry : entriesList) {
                            List<Senses> sensesList = entry.getSenses();
                            for (Senses sense : sensesList) {
                                definitionsList.add(sense.getDefinitions().get(0));
                            }
                        }
                    }
                    res = definitionsList.get(0);
                    if (isRequiredSave) {
                        DaoOperations daoOperations = new DaoOperations(getApplicationContext());
                        daoOperations.open();
                        Words objWord1 = new Words(params[0], res);
                        daoOperations.addWords(objWord1);
                        daoOperations.close();
                    }
                } else {
                    Log.e("error", "null");
                }
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {

            etView.setText(result);
            Log.d("Android : ", "set in tv1");
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }

    }

    public String getMeaning(String[] text) {
        Log.d("Android : ", "Meaning");
        final String app_id = "22f49310";
        final String app_key = "fd2785dc99a13497d2b3e595e91b1c65";
        String s = "";
        try {
            URL url = new URL("https://od-api.oxforddictionaries.com:443/api/v1/entries/en/" + text[0].toLowerCase() + "/definitions");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            s = sb.toString();
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        return s;
    }
}
