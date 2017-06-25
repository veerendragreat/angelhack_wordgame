package com.example.home.wordgame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.wordgame.dtos.Words;

import com.example.home.wordgame.fragments.WordsListFragment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Words} and makes a call to the
 * specified {@link WordsListFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyWordsListRecyclerViewAdapter extends RecyclerView.Adapter<MyWordsListRecyclerViewAdapter.ViewHolder> {

    private final List<Words> mValues;
    private final WordsListFragment.OnListFragmentInteractionListener mListener;

    public MyWordsListRecyclerViewAdapter(List<Words> items, WordsListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_wordslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getId()+"");
        holder.mWordView.setText(mValues.get(position).getWord());
        holder.mMeaningView.setText(mValues.get(position).getMeaning());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mWordView;
        public final TextView mMeaningView;
        public Words mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mWordView = (TextView) view.findViewById(R.id.word);
            mMeaningView = (TextView) view.findViewById(R.id.meaning);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mWordView.getText() + "'";
        }
    }
}
