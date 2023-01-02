package com.example.weathery.home.domain.repository

import com.example.weathery.home.domain.model.WeatherInformation

interface WeatherRepository {
    suspend fun getWeatherInformation(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInformation>
}