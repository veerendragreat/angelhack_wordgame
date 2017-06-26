package com.example.home.wordgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.home.wordgame.R;
import com.example.home.wordgame.fragments.WordsListFragment;
import com.example.home.wordgame.dtos.Words;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        Log.d("Android : ", "Entered into MainActivity constructor.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Android : ", "Entered into MainActivityFragment onCreate method.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //showFragment(new CreateWords());
        Button btAddWords = (Button) findViewById(R.id.button_add_words);
        btAddWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening screen Add words...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent myIntent = new Intent(MainActivity.this, AddWordsActivity.class);
                MainActivity.this.startActivity(myIntent);

            }
        });

        Button btShowWordsList = (Button) findViewById(R.id.button_show_word_list);
        btShowWordsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening word game list screen...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //showFragment(new WordsListFragment(), R.id.container);
                Intent myIntent = new Intent(MainActivity.this, WordsListActivity.class);
                MainActivity.this.startActivity(myIntent);


            }
        });
       /** Button btPlayGame = (Button) findViewById(R.id.button_play_word_game);
        btPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening word game list screen...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //showFragment(new WordsListFragment(), R.id.container);
                Intent myIntent = new Intent(MainActivity.this, WordsListActivity.class);
                MainActivity.this.startActivity(myIntent);


            }
        });**/
        /**
         * //Added this logic in content_main.xml means included in activity_main.xml
         *
         **
         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
         transaction.replace(R.id.gridLayout, new CreateWords(), "fragment").commit();
         */
    }


    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "fragment").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
