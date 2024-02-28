package com.example.weather.domain.usecase

import com.example.weather.common.Resource
import com.example.weather.domain.repo.HomeRepo
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetWeatherForecastUseCase(private val repo: HomeRepo) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<Any>> = flow {
        try {
            emit(Resource.Loading())
            val peopleList = repo.getWeatherForeCase()
            emit(Resource.Success(peopleList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check internet connection"))
        }
    }
}