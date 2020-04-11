package com.almissbah.weather

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class WeatherForcastApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }


}