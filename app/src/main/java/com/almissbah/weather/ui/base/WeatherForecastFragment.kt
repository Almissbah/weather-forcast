package com.almissbah.weather.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.almissbah.weather.utils.hide
import com.almissbah.weather.utils.unHide
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class WeatherForecastFragment : DaggerFragment() {
    var mProgressBar: ProgressBar? = null

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

    fun showSnackbar(view: View, msg: String) {
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
    fun showLoading() {
        mProgressBar?.unHide()
    }

    fun hideLoading() {
        mProgressBar?.hide()
    }

    interface DialogCallback {
        fun onConfirm()
        fun onCancel()
    }
}