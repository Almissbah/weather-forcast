package com.almugdad.takhlesy.di.module

import com.almissbah.weather.ui.forecast.ForecastFragment
import com.almissbah.weather.ui.search.SearchFragment
import com.almissbah.weather.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeForecastFragment(): ForecastFragment
}