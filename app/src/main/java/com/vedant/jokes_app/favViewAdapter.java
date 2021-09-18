package com.vedant.jokes_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vedant.jokes_app.model.Joke;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class favViewAdapter extends RecyclerView.Adapter<fav_jokeViewHolder> {

    private final List<Joke> mJokeList;
    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public favViewAdapter(List jokeList, Context context) {
        mJokeList = jokeList;
        mContext = context;
    }

    @NonNull
    @Override
    public fav_jokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false);
        return new fav_jokeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull fav_jokeViewHolder holder, int position) {


        String jokeText = mJokeList.get(position).getJokeText();
        holder.getTxtFavJoke().setText(jokeText);


    }

    @Override
    public int getItemCount() {
        return mJokeList.size();
    }
}

