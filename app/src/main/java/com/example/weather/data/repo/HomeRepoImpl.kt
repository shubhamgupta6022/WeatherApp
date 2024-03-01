package com.example.weather.data.repo

import com.example.weather.data.service.OpenWeatherMapApi
import com.example.weather.data.remote.dto.forecastreponsedto.ForeCastResponse
import com.example.weather.data.remote.dto.weatherresponsedto.WeatherResponseDto
import com.example.weather.domain.repo.HomeRepo
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(val api: OpenWeatherMapApi) : HomeRepo {
    override suspend fun getWeatherForeCast(): ForeCastResponse {
        return api.getWeatherForeCast()
    }

    override suspend fun getCurrentWeather(): WeatherResponseDto {
        return api.getCurrentWeather()
    }
}