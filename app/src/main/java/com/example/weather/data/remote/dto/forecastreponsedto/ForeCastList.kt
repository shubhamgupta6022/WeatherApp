package com.example.weather.data.remote.dto.forecastreponsedto

import com.example.weather.domain.model.ForeCastWeatherItem

data class ForeCastList(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Int,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun ForeCastList.toForeCastWeatherItem() = ForeCastWeatherItem(
    temp = this.main.temp,
    date = this.dt_txt
)