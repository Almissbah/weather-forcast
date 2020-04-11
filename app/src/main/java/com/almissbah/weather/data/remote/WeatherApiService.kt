package com.almissbah.weather.data.remote

import com.almissbah.weather.data.remote.model.WeatherApiResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/weather")
    fun getCityInfo(@Query("q") cityName: String): Observable<Response<WeatherApiResponse>>

    @GET("/forecast")
    fun fetchForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Observable<Response<List<WeatherApiResponse>>>

}