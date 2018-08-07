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

public class NameSearchActivity extends AppCompatActivity {
    private String name;
    private List<Place> mPlaces;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_search);
        Timber.d("*** Name Search Activity ***");
        getExtras();
        listenCreate();
    }

    private void getExtras() {
        name = getIntent().getStringExtra("name");
    }

    private void listenCreate() {
        NameSearchPresenter presenter = new NameSearchPresenter();
        presenter.getPlaces(name).subscribe(new DisposableObserver<List<Place>>() {
            @Override
            public void onNext(List<Place> places) {
                Timber.d("RxJava got " + places.size() + " places");
                mPlaces = places;
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
        mRecyclerAdapter = new RecyclerAdapter(mPlaces);
        recyclerView.setAdapter(mRecyclerAdapter);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
        private List<Place> rPlaces;
        public RecyclerAdapter(List<Place> items) {
            rPlaces = items;
        }
        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_place, parent, false);
            return new RecyclerHolder(view);
        }
        @Override
        public void onBindViewHolder(RecyclerHolder recyclerholder, int position) {
            Place place = rPlaces.get(position);
            recyclerholder.bindRecyclerData(place);
        }
        @Override
        public int getItemCount(){
            return rPlaces.size();
        }
    }

    private class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView locationText;
        private TextView woeidText;
        private Place place;

        public RecyclerHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            locationText = (TextView)itemView.findViewById(R.id.locationText);
            woeidText = (TextView)itemView.findViewById(R.id.woeidText);
        }
        public void bindRecyclerData(Place newItem) {
            place = newItem;
            locationText.setText(place.title);
            woeidText.setText("" + place.woeid);
        }
        @Override
        public void onClick(View v) {
            Place chosen = mPlaces.get(getAdapterPosition());
            Timber.d("The selected item is " + chosen.title + ", woeid=" + chosen.woeid);
            Intent intent = new Intent(NameSearchActivity.this, ForecastActivity.class);
            intent.putExtra("place", chosen.title);
            intent.putExtra("woeid", "" + chosen.woeid);
            startActivity(intent);
        }
    }


}
