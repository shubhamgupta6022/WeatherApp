package com.example.weather.di

import com.example.weather.common.Constants
import com.example.weather.data.OpenWeatherMapApi
import com.example.weather.data.repo.HomeRepoImpl
import com.example.weather.domain.repo.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOpenWeatherApi(): OpenWeatherMapApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenWeatherMapApi::class.java)

    @Singleton
    @Provides
    fun provideHomeRepo(api: OpenWeatherMapApi) : HomeRepo {
        return HomeRepoImpl(api)
    }
}