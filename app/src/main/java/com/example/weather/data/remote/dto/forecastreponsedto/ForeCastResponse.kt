package com.example.weather.data.remote.dto.forecastreponsedto

data class ForeCastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCastList>,
    val message: Int
)