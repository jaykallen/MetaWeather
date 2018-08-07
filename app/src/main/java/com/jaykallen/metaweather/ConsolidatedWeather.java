
package com.jaykallen.metaweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsolidatedWeather {

    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("weather_state_name")
    @Expose
    public String weatherStateName;
    @SerializedName("weather_state_abbr")
    @Expose
    public String weatherStateAbbr;
    @SerializedName("wind_direction_compass")
    @Expose
    public String windDirectionCompass;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("applicable_date")
    @Expose
    public String applicableDate;
    @SerializedName("min_temp")
    @Expose
    public double minTemp;
    @SerializedName("max_temp")
    @Expose
    public double maxTemp;
    @SerializedName("the_temp")
    @Expose
    public double theTemp;
    @SerializedName("wind_speed")
    @Expose
    public double windSpeed;
    @SerializedName("wind_direction")
    @Expose
    public double windDirection;
    @SerializedName("air_pressure")
    @Expose
    public double airPressure;
    @SerializedName("humidity")
    @Expose
    public long humidity;
    @SerializedName("visibility")
    @Expose
    public double visibility;
    @SerializedName("predictability")
    @Expose
    public long predictability;

}
