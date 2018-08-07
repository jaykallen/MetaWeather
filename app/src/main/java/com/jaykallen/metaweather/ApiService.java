package com.jaykallen.metaweather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // This will result in url: https://www.metaweather.com/api/location/44418/

    @GET("/api/location/{woeid}")
    Call<Weather> queryWeather(@Path("woeid") String woeid);

    @GET("/api/location/search")
    Call<List<Place>> queryCoord(@Query("lattlong") String lattlong);

    @GET("/api/location/search")
    Call<List<Place>> queryName(@Query("query") String name);
}
