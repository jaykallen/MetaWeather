package com.jaykallen.metaweather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private double lat;
    private double lng;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    public void startCoordActivity(Context context) {
        Intent intent = new Intent(context, CoordActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        context.startActivity(intent);
    }

    public void startNameSearchActivity(Context context, String name) {
        Intent intent = new Intent(context, NameSearchActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    public void updateCoord(Context context) {
        if (checkPermissions(context)) {
            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                @Override
                public void gotLocation(Location location){
                    //Got the location!
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    view.showCoordinates(lat + " , " + lng);
                }
            };
            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(context, locationResult);
        } else {
            lat = 0;
            lng = 0;
            view.showCoordinates("Not available");
        }
    }

    private boolean checkPermissions(Context context) {
        return (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

}
