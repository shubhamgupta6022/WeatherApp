package com.example.weather.data.service

import com.example.weather.data.remote.dto.forecastreponsedto.ForeCastResponse
import com.example.weather.data.remote.dto.weatherresponsedto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/forecast")
    suspend fun getWeatherForeCast(
        @Query("q") location: String = "Bengaluru",
        @Query("APPID") apiKey: String = "9b8cb8c7f11c077f8c4e217974d9ee40"
    ): ForeCastResponse

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") location: String = "Bengaluru",
        @Query("APPID") apiKey: String = "9b8cb8c7f11c077f8c4e217974d9ee40"
    ): WeatherResponseDto
}