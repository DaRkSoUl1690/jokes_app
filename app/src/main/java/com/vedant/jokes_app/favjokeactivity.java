package com.vedant.jokes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vedant.jokes_app.fragments.favjokesFragment;


public class favjokeactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favjokeactivity);

        favjokesFragment mFavJokesFragment = favjokesFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fav_jokes_container, mFavJokesFragment).commit();
    }
}