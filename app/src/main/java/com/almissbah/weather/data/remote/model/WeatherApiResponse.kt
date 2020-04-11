package com.almissbah.weather.data.remote.model

data class WeatherApiResponse(
    val weather: List<WeatherInfo>,
    val main: MainInfo,
    val wind: WindInfo, val dt: Long, val dt_text: String
)