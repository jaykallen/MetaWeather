package com.jaykallen.metaweather;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CoordPresenter {
    List<Place> rxPlaces;

    @Inject
    ApiService apiService;

    public CoordPresenter() {
        App.getInstance().getInjector().inject(this);
    }

    public Observable<List<Place>> getPlaces(String lattlong) {
        return Observable.create(new ObservableOnSubscribe<List<Place>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Place>> emitter) throws Exception {
                Call<List<Place>> call = apiService.queryCoord(lattlong);
                Timber.d("Attempting Url: " + call.request().url());
                call.enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                        List<Place> places = response.body();
                        Timber.d("Successful retrofit call got " + places.get(0).title + " and " + places.size() + " places.");
                        emitter.onNext(places);
                        emitter.onComplete();
                    }
                    @Override
                    public void onFailure(Call<List<Place>> call, Throwable t) {
                        Timber.d("Failed retrofit call on " + lattlong);
                    }
                });

            }
        });
    }
}
