package com.almissbah.weather.ui.forecast

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbah.weather.R
import com.almissbah.weather.ui.base.WeatherForecastFragment
import com.almissbah.weather.utils.LocationUtils.Companion.checkPermissions
import com.almissbah.weather.utils.LocationUtils.Companion.isLocationEnabled
import javax.inject.Inject

class ForecastFragment : WeatherForecastFragment() {
    companion object {
        private const val PERMISSION_ID = 101
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var forecastViewModel: ForecastViewModel


    override fun initViewModel() {
        forecastViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[ForecastViewModel::class.java]

    }

    override fun initViews() {

    }

    override fun subscribe() {
        subscribeToLocationUpdates()
        forecastViewModel.cityForecast.observe(viewLifecycleOwner, Observer {
            when (it.action) {
                ForecastViewModel.Action.Success -> {
                    Log.i("cityForecast", it.payload?.city!!.country)
                }
                ForecastViewModel.Action.NotFound -> TODO()
                ForecastViewModel.Action.ShowNetworkError -> TODO()
            }
        })
    }

    override fun unSubscribe() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_forcast, container, false)

        return root
    }


    private fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                subscribeToLocationUpdates()
            }
        }
    }

    private fun subscribeToLocationUpdates() {
        if (checkPermissions(context!!)) {
            forecastViewModel.getLocationData().observe(viewLifecycleOwner, Observer {
                Log.i("Location", " working ${it.lat}  ${it.lon}")
                forecastViewModel.getCityForecast(it)
            })

            if (!isLocationEnabled(context!!)) {
                launchSettingsScreen()
            }
        } else {
            requestPermissions()
        }
    }

    private fun launchSettingsScreen() {
        showToast("Turn on location")
        val intent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }


}
