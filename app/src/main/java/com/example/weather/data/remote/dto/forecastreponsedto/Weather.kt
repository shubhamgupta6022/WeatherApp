package com.example.weather.data.remote.dto.forecastreponsedto

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)