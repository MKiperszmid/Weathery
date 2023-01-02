package com.example.weathery.home.domain.model

data class WeatherInformation(
    val actualTemperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val windSpeed: Double,
    val image: String
)