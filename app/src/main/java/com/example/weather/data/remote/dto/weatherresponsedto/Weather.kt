package com.example.weather.data.remote.dto.weatherresponsedto

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)