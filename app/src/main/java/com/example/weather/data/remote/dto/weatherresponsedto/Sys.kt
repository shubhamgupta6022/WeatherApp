package com.example.weather.data.remote.dto.weatherresponsedto

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)