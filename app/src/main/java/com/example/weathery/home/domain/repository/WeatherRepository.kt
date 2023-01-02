package com.example.weathery.home.domain.repository

import com.example.weathery.home.domain.model.WeatherInformation

interface WeatherRepository {
    suspend fun getWeatherInformation(
        city: String
    ): Result<WeatherInformation>

    suspend fun getWeatherByLatitudeAndLongitude(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInformation>
}