package com.vedant.jokes_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.vedant.jokes_app.controllers.CardDataAdapter;
import com.vedant.jokes_app.controllers.JokeLikeListener;
import com.vedant.jokes_app.model.Joke;
import com.vedant.jokes_app.model.jokeManager;
import com.wenchao.cardstack.CardStack;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardStack.CardEventListener, JokeLikeListener {

    CardStack mCardStack;
    CardDataAdapter mCardAdapter;
    private final List<Joke> allJokes = new ArrayList<>();
    private jokeManager mJokeManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardStack = findViewById(R.id.container);
        mJokeManager = new jokeManager(this);
        mCardStack = (CardStack) findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_layout);
        mCardStack.setStackMargin(20);
        mCardAdapter = new CardDataAdapter(this, 0);

        try {
            JSONObject rootObject = new JSONObject(loadJSONFromAsset());

            JSONArray fatJokes = rootObject.getJSONArray("fat");
            addJokesToArrayList(fatJokes, allJokes);

            JSONArray stupidJokes = rootObject.getJSONArray("stupid");
            addJokesToArrayList(stupidJokes, allJokes);

            JSONArray uglyJokes = rootObject.getJSONArray("ugly");
            addJokesToArrayList(uglyJokes, allJokes);

            JSONArray nastyJokes = rootObject.getJSONArray("nasty");
            addJokesToArrayList(nastyJokes, allJokes);

            JSONArray hairyJokes = rootObject.getJSONArray("hairy");
            addJokesToArrayList(hairyJokes, allJokes);

            JSONArray baldJokes = rootObject.getJSONArray("bald");
            addJokesToArrayList(baldJokes, allJokes);

            JSONArray oldJokes = rootObject.getJSONArray("old");
            addJokesToArrayList(oldJokes, allJokes);

            JSONArray poorJokes = rootObject.getJSONArray("poor");
            addJokesToArrayList(poorJokes, allJokes);

            JSONArray shortJokes = rootObject.getJSONArray("short");
            addJokesToArrayList(shortJokes, allJokes);

            JSONArray skinnyJokes = rootObject.getJSONArray("skinny");
            addJokesToArrayList(skinnyJokes, allJokes);

            JSONArray tallJokes = rootObject.getJSONArray("tall");
            addJokesToArrayList(tallJokes, allJokes);

            JSONArray likeJokes = rootObject.getJSONArray("like");
            addJokesToArrayList(likeJokes, allJokes);


        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Joke joke : allJokes) {
            mCardAdapter.add(joke.getJokeText());
        }
        mCardStack.setAdapter(mCardAdapter);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("jokes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    private void addJokesToArrayList(JSONArray jsonArray, List<Joke> arrayList) {
        try {
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(new Joke(jsonArray.getString(i), false));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jokeIsLiked(@NonNull Joke joke) {


        if (joke.isJokeLiked()) {

           mJokeManager.saveJoke(joke);

        } else {

            mJokeManager.deleteJoke(joke);
        }

    }


    @Override
    public boolean swipeEnd(int section, float distance) {
        return distance > 300;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void discarded(int mIndex, int direction) {

    }

    @Override
    public void topCardTapped() {

    }
// menu options

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(MainActivity.this,favjokeactivity.class));
        return super.onOptionsItemSelected(item);
    }
}
