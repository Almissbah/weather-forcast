package com.almissbah.weather.data.remote.repo

import com.almissbah.weather.data.remote.WeatherApiService
import com.almissbah.weather.data.remote.model.City5DaysForecast
import com.almissbah.weather.data.remote.model.CityForecastRequest
import com.almissbah.weather.data.remote.model.CityWeather
import com.almissbah.weather.data.remote.model.CityWeatherRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class AppRepository(private val weatherApiService: WeatherApiService) : CityForecastRepo,
    CityWeatherRepo {

    override fun getCityForecast(request: CityForecastRequest): Observable<Response<City5DaysForecast>> {
        return weatherApiService.fetchForecastByCoordinates(request.lat, request.lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCityWeather(request: CityWeatherRequest): Observable<Response<CityWeather>> {
        return weatherApiService.getCityInfo(request.cityName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}