package com.vedant.jokes_app.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vedant.jokes_app.R;
import com.vedant.jokes_app.model.Joke;

import androidx.annotation.NonNull;

public class CardDataAdapter extends ArrayAdapter<String> {


    private boolean clicked = true;

    private Joke mJoke;

    private final SharedPreferences mSharedPreferences;
    private final JokeLikeListener mJokeLikeListener;

    public CardDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mJokeLikeListener = (JokeLikeListener) context;

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent) {
        //supply the layout for your card
        TextView v = (contentView.findViewById(R.id.content));
        v.setText(getItem(position));

        ImageButton likeButton = contentView.findViewById(R.id.likeButton);


        if (mSharedPreferences.contains(getItem(position))) {
            likeButton.setImageResource(R.drawable.like_filled);
            clicked = false;
        } else {
            clicked = true;
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (clicked) {

                    likeButton.setImageResource(R.drawable.like_filled);
                    clicked = false;
                    mJoke = new Joke(getItem(position), true);
                    mJokeLikeListener.jokeIsLiked(mJoke);
                } else {

                    likeButton.setImageResource(R.drawable.like_empty);
                    clicked = true;
                    mJoke = new Joke(getItem(position), false);
                    mJokeLikeListener.jokeIsLiked(mJoke);

                }


            }
        });

        ImageButton shareButton = contentView.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Create an ACTION_SEND Intent*/
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                /*This will be the actual content you wish you share.*/
                String shareBody = v.getText().toString();
                /*The type of the content is text, obviously.*/
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mama Joke!");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                /*Fire!*/
                view.getContext().startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });

        return contentView;
    }

}
