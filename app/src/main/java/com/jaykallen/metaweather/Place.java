
package com.jaykallen.metaweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("distance")
    @Expose
    public long distance;
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

}
