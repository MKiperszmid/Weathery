package com.example.weathery.home.domain.usecase

import com.example.weathery.home.domain.model.WeatherInformation
import com.example.weathery.home.domain.repository.WeatherRepository

class GetWeatherByLocationUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<WeatherInformation> {
        return repository.getWeatherInformation(city)
    }
}