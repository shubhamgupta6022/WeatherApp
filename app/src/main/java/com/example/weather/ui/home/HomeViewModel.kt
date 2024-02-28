package com.example.weather.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.Constants
import com.example.weather.common.Resource
import com.example.weather.data.OpenWeatherMapApi
import com.example.weather.data.repo.HomeRepoImpl
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetWeatherForecastUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"
    init {
//        getForeCastResponse()
        getCurrentWeatherResponse()
    }

    private fun getCurrentWeatherResponse() {
        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherMapApi::class.java)
        val repo = HomeRepoImpl(api)
        val getCurrentWeatherUseCase = GetCurrentWeatherUseCase(repo)
        getCurrentWeatherUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    Log.d(TAG, "getCurrentWeatherUseCase Success: ${it.data}")
                }
                is Resource.Error -> {
                    Log.d(TAG, "getCurrentWeatherUseCase Error: ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d(TAG, "getCurrentWeatherUseCase Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getForeCastResponse() {
        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherMapApi::class.java)
        val repo = HomeRepoImpl(api)
        val getWeatherForecastUseCase = GetWeatherForecastUseCase(repo)
        getWeatherForecastUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    Log.d(TAG, "getWeatherForecastUseCase Success: ${it.data}")
                }
                is Resource.Error -> {
                    Log.d(TAG, "getWeatherForecastUseCase Error: ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d(TAG, "getWeatherForecastUseCase Loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}