package com.almissbah.weather.ui.base

import android.os.Bundle
import android.view.View

abstract class WeatherForecastFragment : BaseFragment() {

    abstract fun initViewModel()

    abstract fun initViews()

    abstract fun subscribe()

    abstract fun unSubscribe()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        subscribe()
    }


    override fun onDestroy() {
        unSubscribe()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }


}