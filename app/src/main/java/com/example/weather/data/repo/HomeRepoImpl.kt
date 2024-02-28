package com.example.weather.data.repo

import com.example.weather.data.OpenWeatherMapApi
import com.example.weather.data.remote.dto.forecastreponsedto.ForeCastResponse
import com.example.weather.data.remote.dto.weatherresponsedto.WeatherResponseDto
import com.example.weather.domain.repo.HomeRepo

class HomeRepoImpl(val api: OpenWeatherMapApi) : HomeRepo {
    override suspend fun getWeatherForeCase(): ForeCastResponse {
        return api.getWeatherForeCast()
    }

    override suspend fun getCurrentWeather(): WeatherResponseDto {
        return api.getCurrentWeather()
    }
}