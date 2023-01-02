package com.example.weathery.home.data

import com.example.weathery.home.data.mapper.toDomain
import com.example.weathery.home.data.remote.WeatherApi
import com.example.weathery.home.domain.model.WeatherInformation
import com.example.weathery.home.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherInformation(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInformation> {
        return try {
            val result = api.getWeatherByLocation(latitude, longitude)
            Result.success(result.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}