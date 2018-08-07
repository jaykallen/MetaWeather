package com.jaykallen.metaweather;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ForecastPresenter {
    @Inject
    ApiService apiService;

    public ForecastPresenter() {
        App.getInstance().getInjector().inject(this);
    }

    Observable<Weather> getWeather(String woeid) {
        return Observable.create(new ObservableOnSubscribe<Weather>() {
            @Override
            public void subscribe(ObservableEmitter<Weather> emitter) throws Exception {
                Call<Weather> call = apiService.queryWeather(woeid);
                Timber.d("Attempting Url: " + call.request().url());
                call.enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Weather weather = response.body();
                        Timber.d("Got " + weather.title + "'s forecast from " + woeid);
                        emitter.onNext(weather);
                        emitter.onComplete();
                    }
                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Timber.d("Failed retrofit call on " + woeid);
                    }
                });
            }
        });
    }

}
