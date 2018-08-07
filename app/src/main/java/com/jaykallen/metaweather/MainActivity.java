package com.jaykallen.metaweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import timber.log.Timber;

// Created by Jay Kallen on 8/6/2018
// Note: I'm starting this Monday afternoon, so not a lot of time to complete.
// I will use Retrofit with Dagger2 and RxJava
// MainActivity in MVP Design Pattern to demonstrate that I know how to do it.
// Other activities may use it depending on how much time I have.
// Normally I would put package names under Uncle Bob's Clean Architecture if I had time.

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private TextView coordText;
    private EditText searchEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordText = findViewById(R.id.coordText);
        searchEdit = findViewById(R.id.searchEdit);

        Timber.d("*** Main Activity ***");
        // This could also be replaced with Dagger Injection
        presenter = new MainPresenter(this);
        presenter.updateCoord(this);
    }

    public void showCoordinates(String coordString) {
        coordText.setText(coordString);
    }


    public void onCoordSearchClick(View view) {
        presenter.startCoordActivity(this);
    }

    public void onNameSearchClick(View view) {
        presenter.startNameSearchActivity(this, searchEdit.getText().toString());
    }

}