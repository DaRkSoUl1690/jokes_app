package com.vedant.jokes_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.vedant.jokes_app.R;
import com.vedant.jokes_app.favViewAdapter;
import com.vedant.jokes_app.model.Joke;
import com.vedant.jokes_app.model.jokeManager;

import java.util.ArrayList;
import java.util.List;


public class favjokesFragment extends Fragment {
    RecyclerView mRecyclerView;
    favViewAdapter mFavJokesAdapter;
    jokeManager mJokeManager;
    private List<Joke> mJokeList = new ArrayList<>();

    private Joke deletedJoke;

    public favjokesFragment() {
        // Required empty public constructor
    }


    public static favjokesFragment newInstance() {
        return new favjokesFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mJokeManager = new jokeManager(context);
        mJokeList.clear();
        if (mJokeManager.retrieveJokes().size() > 0) {
            mJokeList.addAll(mJokeManager.retrieveJokes());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favjokes, container, false);
        if (view != null) {
            mRecyclerView = view.findViewById(R.id.rv);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mFavJokesAdapter = new favViewAdapter(mJokeList, getContext());
            mRecyclerView.setAdapter(mFavJokesAdapter);


        }
        return view;
    }
}