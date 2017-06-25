package com.example.home.wordgame.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.home.wordgame.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.os.AsyncTask;
import android.widget.Toast;


import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.repository.DaoOperations;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class ShareActivity extends AppCompatActivity {
    TextView tvShareWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent incomingData = getIntent();
        String action = incomingData.getAction();
        String type = incomingData.getType();
        incomingData.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        Log.d("Android : ", "Before initialization");
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            incomingData.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
            showView(incomingData);
        }


    }

    public void showView(Intent incomingData) {
        String sharedText = incomingData.getStringExtra(Intent.EXTRA_TEXT);

        if (sharedText != null) {
            tvShareWord = (TextView) findViewById(R.id.share_word);
            tvShareWord.setText(sharedText);

            new ExecuteTask().execute(sharedText);
        }

    }

    class ExecuteTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
        }


        @Override
        protected String doInBackground(String... params) {
            Log.d("Android : ", "doInbackground");
            String res = getMeaning(params);

            try {
                Gson map = new Gson();
                if(res != null || res!="") {
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

                    DaoOperations daoOperations = new DaoOperations(getApplicationContext());
                    daoOperations.open();
                    Words objWord1 = new Words(params[0], res);
                    daoOperations.addWords(objWord1);
                    daoOperations.close();
                }else{
                    Log.e("error","null");
                }
            } catch (Exception e) {
                Log.e("error",e.getMessage());
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView share_meaning = (TextView) findViewById(R.id.share_meaning);
            share_meaning.setText(result);
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
                Log.d("Exception",e.getMessage());
        }
        return s;
    }
}
