package com.jaykallen.metaweather;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private InjectorComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        // Setup Dagger2
        instance = this;
        injector = DaggerInjectorComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();

        // Setup Timber
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                super.log(priority, tag + ".JK", message, t);
            }
        });
    }

    public InjectorComponent getInjector() {
        return injector;
    }



}
