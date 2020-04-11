package com.almissbah.weather.ui.base

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class WeatherForecastFragment : DaggerFragment() {


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


    fun showSnackbar(view: View, msg: String) {
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    interface DialogCallback {
        fun onConfirm()
        fun onCancel()
    }
}