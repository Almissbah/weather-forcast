package com.almugdad.takhlesy.di.module

import com.almissbah.weather.ui.forcast.ForcastFragment
import com.almissbah.weather.ui.home.HomeFragment
import com.almissbah.weather.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeForcastFragment(): ForcastFragment
}