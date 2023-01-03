package com.example.weathery.home.domain.model

data class WeatherInformation(
    val temperature: Temperature,
    val windSpeed: Double,
    val image: String,
    val weatherType: String,
    val weatherDescription: String
)

data class Temperature(
    val current: Double,
    val minimum: Double,
    val maximum: Double,
)
