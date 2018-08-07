
package com.jaykallen.metaweather;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("consolidated_weather")
    @Expose
    public List<ConsolidatedWeather> consolidatedWeather = null;
    @SerializedName("time")
    @Expose
    public String time;
    @SerializedName("sun_rise")
    @Expose
    public String sunRise;
    @SerializedName("sun_set")
    @Expose
    public String sunSet;
    @SerializedName("timezone_name")
    @Expose
    public String timezoneName;
    @SerializedName("parent")
    @Expose
    public Parent parent;
    @SerializedName("sources")
    @Expose
    public List<Source> sources = null;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("location_type")
    @Expose
    public String locationType;
    @SerializedName("woeid")
    @Expose
    public long woeid;
    @SerializedName("latt_long")
    @Expose
    public String lattLong;
    @SerializedName("timezone")
    @Expose
    public String timezone;

}
