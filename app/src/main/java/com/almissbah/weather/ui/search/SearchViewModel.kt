package com.almissbah.weather.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.weather.data.remote.CallbackWrapper
import com.almissbah.weather.data.remote.model.CityWeather
import com.almissbah.weather.data.remote.model.CityWeatherRequest
import com.almissbah.weather.data.remote.repo.CityWeatherRepo
import com.almissbah.weather.utils.SearchInputUtils
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val weatherRepo: CityWeatherRepo) : ViewModel() {
    companion object {
        const val TAG = "SearchViewModel"
    }

    private val _queryValidator = MutableLiveData<SearchInputUtils.CitiesCount>()
    val queryValidator: LiveData<SearchInputUtils.CitiesCount> = _queryValidator
    var cityWeatherList = mutableListOf<CityWeather>()

    private fun fetchWeatherInfo(list: List<String>) {
        cityWeatherList = mutableListOf()

        list.forEach {
            val request = CityWeatherRequest(it)

            val callback = CallbackWrapper(object :
                CallbackWrapper.HttpCallback<CityWeather> {
                override fun onSuccess(t: CityWeather?) {
                    Log.i(TAG, "$it temp:${t!!.mainInfo!!.maxTemp}")
                }

                override fun onNetworkError() {

                }

                override fun onServerError() {

                }

                override fun onNotFound() {
                    Log.i(TAG, "$it NOT FOUND")
                }

            })

            weatherRepo.getCityWeather(request).subscribe(callback)
        }
    }

    fun validateInput(query: String) {
        val list = SearchInputUtils.getValidCityList(query)
        val result = SearchInputUtils.validateCitiesCount(list.size)
        if (result == SearchInputUtils.CitiesCount.Valid) {
            fetchWeatherInfo(list)
        } else {
            _queryValidator.postValue(result)
        }
    }
}