package com.example.weather.data.remote.dto.forecastreponsedto

import com.example.weather.domain.model.ForeCastData

data class ForeCastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCastList>,
    val message: Int
)

fun ForeCastResponse.toForeCastData() = ForeCastData(
    name = this.city.name,
    list = this.list.map { it.toForeCastWeatherItem() }
)