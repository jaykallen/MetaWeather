package com.jaykallen.metaweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Places {

    @SerializedName("places")
    @Expose
    public List<Place> places = null;

}
