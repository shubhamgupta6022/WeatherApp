package com.example.weather.domain.model

data class ForeCastData(
    val name: String,
    val list: List<ForeCastWeatherItem>
)