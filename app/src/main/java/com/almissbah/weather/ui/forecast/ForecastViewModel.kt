package com.almissbah.weather.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.weather.data.remote.repo.CityForecastRepo
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val forecastRepo: CityForecastRepo) :
    ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}