package com.example.home.wordgame.activities;

import android.app.ListActivity;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.home.wordgame.R;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.fragments.WordsListFragment;
import com.example.home.wordgame.repository.DaoOperations;

import java.util.List;
import java.util.Locale;

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

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_words_list_activity, fragment, "fragment").commit();
    }


    @Override
    public void onListFragmentInteraction(Words item) {
        Log.d("Android : ", item.getWord());

        t1.speak("Word is " + item.getWord() + " And Meaning is " + item.getMeaning(), TextToSpeech.QUEUE_FLUSH, null);
    }
}
