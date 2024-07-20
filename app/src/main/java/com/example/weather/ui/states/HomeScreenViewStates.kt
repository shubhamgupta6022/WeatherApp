package com.example.weather.ui.states

import com.example.weather.domain.model.ForeCastData
import com.example.weather.domain.model.WeatherData

sealed class HomeScreenViewStates {
    data class WeatherResponse(val list: WeatherData) : HomeScreenViewStates()
    data class WeatherForeCastResponse(val list: ForeCastData) : HomeScreenViewStates()
    data class ApiError(val message: String? = null) : HomeScreenViewStates()
    data class PageLoading(val data: Any? = null) : HomeScreenViewStates()
    object Idle : HomeScreenViewStates()
}
