package com.example.weathery.home.presentation

data class HomeState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val defaultCities: List<String> = cities
)

private val cities = listOf(
    "Montevideo", "Sao Paulo", "Buenos Aires", "London", "Munich"
)
