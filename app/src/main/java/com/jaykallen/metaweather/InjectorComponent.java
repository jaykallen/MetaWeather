package com.jaykallen.metaweather;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface InjectorComponent {

    void inject(CoordPresenter coordPresenter);

    void inject(ForecastPresenter forecastPresenter);

    void inject(NameSearchPresenter nameSearchPresenter);
}
