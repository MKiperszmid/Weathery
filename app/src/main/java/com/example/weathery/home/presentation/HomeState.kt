package com.example.weathery.home.presentation

import com.example.weathery.home.domain.model.WeatherInformation

data class HomeState(
    val defaultCities: Map<String, WeatherInfoState> = citiesMap
)

data class WeatherInfoState(
    val isLoading: Boolean = false,
    val information: WeatherInformation? = null
)

private val citiesMap = mapOf(
    "Montevideo" to WeatherInfoState(),
    "Sao Paulo" to WeatherInfoState(),
    "Buenos Aires" to WeatherInfoState(),
    "London" to WeatherInfoState(),
    "Munich" to WeatherInfoState(),
)

