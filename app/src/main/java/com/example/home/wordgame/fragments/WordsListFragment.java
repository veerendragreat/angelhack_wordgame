package com.example.home.wordgame.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.home.wordgame.MyWordsListRecyclerViewAdapter;
import com.example.home.wordgame.R;
import com.example.home.wordgame.activities.AddWordsActivity;
import com.example.home.wordgame.activities.MainActivity;
import com.example.home.wordgame.activities.WordsListActivity;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.repository.DaoOperations;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WordsListFragment extends Fragment {

    int startIndex = 1;
    int endIndex = 1;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WordsListFragment() {
        Log.d("Android : ", "Entered into WordsListFragment constructor.");
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WordsListFragment newInstance(int columnCount) {
        Log.d("Android : ", "Entered into WordsListFragment constructor.");
        WordsListFragment fragment = new WordsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Android : ", "Entered into WordsListFragment onCreate.");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View buttonViewInflate = inflater.inflate(R.layout.fragment_wordslist, container, false);
/*
        Button btNextWords = (Button) buttonViewInflate.findViewById(R.id.wl_bt_next);
        btNextWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Android : ", "Entered next words list button clicked.");
                Snackbar.make(view, "Your Next Words List coming Please wait...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.words_list_view, WordsListFragment.this, "fragment").commit();

                *//**Intent myIntent = new Intent(WordsListFragment.this, MainActivity.class);
         AddWordsActivity.this.startActivity(myIntent);**//*
            }
        });*/

        // Set the adapter
        View rv = inflater.inflate(R.layout.fragment_wordslist_list, container, false);

        RecyclerView view = (RecyclerView) rv.findViewById(R.id.words_list_view);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            DaoOperations daoOperations = new DaoOperations(getContext());
            daoOperations.open();
            int swap = endIndex;
            startIndex = endIndex;
            endIndex = swap + 5;

            List<Words> allwords = daoOperations.getAllwords(false, startIndex, endIndex);
            daoOperations.close();

            recyclerView.setAdapter(new MyWordsListRecyclerViewAdapter(allwords, mListener));

            // RelativeLayout footerLayout = (RelativeLayout) inflater.inflate(R.layout.activity_words_list,null);
            //Button btnPostYourEnquiry = (Button) footerLayout.findViewById(R.id.bt_next);

            //recyclerView.addView(footerLayout);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Words item);
    }
}
