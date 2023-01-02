package com.example.weathery.home.data

import com.example.weathery.home.domain.model.WeatherInformation
import com.example.weathery.home.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeatherInformation(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInformation> {
        return Result.failure(Throwable(""))
    }
}