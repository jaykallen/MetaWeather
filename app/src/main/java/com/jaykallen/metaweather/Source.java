
package com.jaykallen.metaweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("crawl_rate")
    @Expose
    public long crawlRate;

}
