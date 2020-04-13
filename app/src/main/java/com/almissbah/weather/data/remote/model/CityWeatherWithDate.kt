package com.almissbah.weather.data.remote.model

import com.google.gson.annotations.SerializedName

data class CityWeatherWithDate(
    @SerializedName("dt_text") val date: String,
    @SerializedName("name") val cityName: String?,
    @SerializedName("weather") val weatherDescription: List<WeatherDescription>?,
    @SerializedName("main") val mainInfo: MainInfo?,
    @SerializedName("wind") val windInfo: WindInfo?,
    @SerializedName("dt") val timeStamp: Long?
)