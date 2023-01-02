package com.example.weathery.home.domain.model

data class WeatherInformation(
    val currentTemperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val windSpeed: Double,
    val image: String,
    val weatherType: String,
    val latitude: Double,
    val longitude: Double
)