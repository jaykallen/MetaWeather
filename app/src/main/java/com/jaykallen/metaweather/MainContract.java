package com.jaykallen.metaweather;

import android.content.Context;

public interface MainContract {

    interface View {
        void showCoordinates(String coordString);
    }

    interface Presenter {
        void updateCoord(Context context);
        void startCoordActivity(Context context);
        void startNameSearchActivity(Context context, String name);
    }

}
