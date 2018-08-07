package com.jaykallen.metaweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public class ForecastActivity extends AppCompatActivity {
    private String place;
    private String woeid;
    private Weather mWeather;
    private List<ConsolidatedWeather> consolidatedWeathers;
    private RecyclerAdapter mRecyclerAdapter;
    private TextView forecastText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        forecastText = findViewById(R.id.forecastText);
        Timber.d(" *** Forecast Activity ***");
        getExtras();
        listenCreate();
    }

    private void getExtras() {
        place = getIntent().getStringExtra("place");
        woeid = getIntent().getStringExtra("woeid");
        forecastText.setText("The forecast for " + place + " is:");
        Timber.d("The woeid is " + woeid);
    }

    private void listenCreate() {
        ForecastPresenter forecastPresenter = new ForecastPresenter();
        forecastPresenter.getWeather(woeid).subscribe(new DisposableObserver<Weather>() {
            @Override
            public void onNext(Weather weather) {
                Timber.d("RxJava got " + weather.title + "'s forecast");
                mWeather = weather;
                consolidatedWeathers = mWeather.consolidatedWeather;
                setupRecycler();
            }
            @Override
            public void onError(Throwable e) {
                Timber.d("RxJava Error " + e);
            }
            @Override
            public void onComplete() {
                Timber.d("RxJava Completed");
            }
        });
    }


    private void setupRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mRecyclerAdapter = new RecyclerAdapter(consolidatedWeathers);
        recyclerView.setAdapter(mRecyclerAdapter);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
        private List<ConsolidatedWeather> rConsolidatedWeathers;
        public RecyclerAdapter(List<ConsolidatedWeather> items) {
            rConsolidatedWeathers = items;
        }
        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_forecast, parent, false);
            return new RecyclerHolder(view);
        }
        @Override
        public void onBindViewHolder(RecyclerHolder recyclerholder, int position) {
            ConsolidatedWeather place = rConsolidatedWeathers.get(position);
            recyclerholder.bindRecyclerData(place);
        }
        @Override
        public int getItemCount(){
            return rConsolidatedWeathers.size();
        }
    }

    private class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView dateText;
        private TextView descText;
        private TextView minTempText;
        private TextView maxTempText;
        private ConsolidatedWeather cWeather;

        public RecyclerHolder(View itemView) {
            super(itemView);
            dateText = (TextView)itemView.findViewById(R.id.dateText);
            descText = (TextView)itemView.findViewById(R.id.descText);
            minTempText = (TextView)itemView.findViewById(R.id.minTempText);
            maxTempText = (TextView)itemView.findViewById(R.id.maxTempText);
        }
        public void bindRecyclerData(ConsolidatedWeather newItem) {
            cWeather = newItem;
            dateText.setText(cWeather.applicableDate);
            descText.setText("" + cWeather.weatherStateName);
            minTempText.setText("Low: " + cWeather.minTemp);
            maxTempText.setText("High: " + cWeather.maxTemp);
        }
    }


}
