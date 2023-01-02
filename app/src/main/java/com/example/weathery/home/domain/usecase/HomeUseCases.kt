package com.example.weathery.home.domain.usecase

data class HomeUseCases(
    val getWeatherByCoordinates: GetWeatherByCoordinatesUseCase,
    val getWeatherByLocation: GetWeatherByLocationUseCase
)
