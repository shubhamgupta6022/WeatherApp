package com.example.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.Constants
import com.example.weather.common.Resource
import com.example.weather.common.toCelsius
import com.example.weather.data.service.OpenWeatherMapApi
import com.example.weather.data.repo.HomeRepoImpl
import com.example.weather.domain.model.ForeCastData
import com.example.weather.domain.model.WeatherData
import com.example.weather.ui.states.HomeScreenViewStates
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private var _states = MutableSharedFlow<HomeScreenViewStates>()
    val states = _states.asSharedFlow()

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        getCurrentWeatherResponse()
    }

    fun getCurrentWeatherResponse() {
        getCurrentWeatherUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    if (it.data is WeatherData) {
                        scope.launch {
                            _states.emit(HomeScreenViewStates.WeatherResponse(it.data.copy(temp = it.data.temp.toCelsius())))
                        }
                        getForeCastResponse()
                    }
                }

                is Resource.Error -> {
                    scope.launch {
                        _states.emit(HomeScreenViewStates.ApiError(it.message))
                    }
                }

                is Resource.Loading -> {
                    scope.launch {
                        _states.emit(HomeScreenViewStates.PageLoading())
                    }
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
            when (it) {
                is Resource.Success -> {
                    scope.launch {
                        if (it.data is ForeCastData) {
                            _states.emit(HomeScreenViewStates.WeatherForeCastResponse(it.data))
                        }
                    }
                }

                is Resource.Error -> {}

                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}