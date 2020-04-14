package com.almissbah.weather.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.weather.data.Resource
import com.almissbah.weather.data.remote.CallbackWrapper
import com.almissbah.weather.data.remote.model.City5DaysForecast
import com.almissbah.weather.data.remote.model.CityForecastRequest
import com.almissbah.weather.data.remote.repo.CityForecastRepo
import com.almissbah.weather.utils.LocationData
import com.almissbah.weather.utils.LocationLiveData
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val forecastRepo: CityForecastRepo,
    private val locationLiveData: LocationLiveData
) :
    ViewModel() {
    fun getLocationData() = locationLiveData

    enum class Action { Success, NotFound, ShowNetworkError }

    private val _cityForecast = MutableLiveData<Resource<City5DaysForecast, Action>>()
    val cityForecast: LiveData<Resource<City5DaysForecast, Action>> = _cityForecast

    fun getCityForecast(it: LocationData) {
        forecastRepo.getCityForecast(CityForecastRequest(it.lat, it.lon))
            .subscribe(CallbackWrapper(object : CallbackWrapper.HttpCallback<City5DaysForecast> {
                override fun onSuccess(t: City5DaysForecast?) {
                    _cityForecast.postValue(Resource(t, Action.Success, ""))
                }

                override fun onNetworkError() {
                    _cityForecast.postValue(Resource(null, Action.Success, ""))
                }

                override fun onServerError() {
                    _cityForecast.postValue(Resource(null, Action.ShowNetworkError, ""))
                }

                override fun onNotFound() {
                    _cityForecast.postValue(Resource(null, Action.NotFound, ""))
                }
            }))
    }

}