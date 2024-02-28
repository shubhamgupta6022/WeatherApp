package com.example.weather.domain.repo

import com.example.weather.data.remote.dto.forecastreponsedto.ForeCastResponse
import com.example.weather.data.remote.dto.weatherresponsedto.WeatherResponseDto

interface HomeRepo {
    suspend fun getWeatherForeCast(): ForeCastResponse
    suspend fun getCurrentWeather(): WeatherResponseDto
}