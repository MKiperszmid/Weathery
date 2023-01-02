package com.example.weathery.home.presentation

import com.example.weathery.home.domain.model.WeatherInformation

data class HomeState(
    val cities: Map<String, WeatherInfoState> = citiesMap
)

private val citiesMap = mapOf(
    "Montevideo" to WeatherInfoState.Loading,
    "Sao Paulo" to WeatherInfoState.Loading,
    "Buenos Aires" to WeatherInfoState.Loading,
    "London" to WeatherInfoState.Loading,
    "Munich" to WeatherInfoState.Loading,
    "asahs" to WeatherInfoState.Loading,
)

sealed interface WeatherInfoState {
    object Loading : WeatherInfoState
    object Error : WeatherInfoState // We can add an error message to this if needed
    data class Loaded(val information: WeatherInformation) : WeatherInfoState
}

