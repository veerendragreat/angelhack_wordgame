package com.example.home.wordgame.activities;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.os.Build;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.home.wordgame.R;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.fragments.WordsListFragment;
import com.example.home.wordgame.repository.DaoOperations;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVWriter;

//public class WordsListActivity extends ListActivity {
public class WordsListActivity extends AppCompatActivity implements WordsListFragment.OnListFragmentInteractionListener {
    TextToSpeech t1;

    public WordsListActivity() {
        Log.d("Android : ", "Entered WordsListActivity constructor.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Android : ", "Entered WordsListActivity onCreate method.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        showFragment(new WordsListFragment());
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
        if (shouldAskPermissions()) {
            askPermissions();
        }

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_words_list_activity, fragment, "fragment").commit();
    }


    @Override
    public void onListFragmentInteraction(Words item) {
        Log.d("Android : ", item.getWord());
        t1.setSpeechRate(0.8f);
        t1.speak("Word is " + item.getWord() + " And Meaning is " + item.getMeaning(), TextToSpeech.QUEUE_FLUSH, null);
    }

    public void exportWords(View v) throws Exception {
        DaoOperations daoOperations = new DaoOperations(getApplicationContext());
        daoOperations.open();
        List<Words> allwords = daoOperations.getAllwords(false, 1, 1);
        List<String[]> wordsArray = new ArrayList<String[]>();
        String words[];
        Words word;
        if(allwords.size()>0){
        for (int i = 0; i < allwords.size(); i++) {
            word = allwords.get(i);
            words = new String[4];
            words[0] = String.valueOf(word.getId());
            words[1] = word.getWord();
            words[2] = word.getMeaning();
            words[3] = word.getDescription();
            wordsArray.add(words);

        }
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "WordsList.csv");
        CSVWriter writer = new CSVWriter(new FileWriter(file));

        String arrStr1[] = {"Id", "Word", "Meaning", "Description"};
        writer.writeNext(arrStr1);

        writer.writeAll(wordsArray);
        writer.close();


        daoOperations.close();
        Toast.makeText(getApplicationContext(), "Exported as WordsList.csv", Toast.LENGTH_LONG).show();
    }else

            Toast.makeText(getApplicationContext(), "No words to export", Toast.LENGTH_LONG).show();
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }
}
