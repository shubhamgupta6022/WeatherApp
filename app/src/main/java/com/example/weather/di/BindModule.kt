package com.example.weather.di

import com.example.weather.data.repo.HomeRepoImpl
import com.example.weather.domain.repo.HomeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class BindModule {
    @Binds
    abstract fun bindHomeRepoImpl(homeRepoImpl: HomeRepoImpl) : HomeRepo
}