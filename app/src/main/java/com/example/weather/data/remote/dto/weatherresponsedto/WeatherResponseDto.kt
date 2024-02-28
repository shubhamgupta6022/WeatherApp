package com.example.weather.data.remote.dto.weatherresponsedto

import com.example.weather.domain.model.WeatherData

data class WeatherResponseDto(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherResponseDto.toWeatherData() = WeatherData(
    name = this.name,
    temp = this.main.temp
)