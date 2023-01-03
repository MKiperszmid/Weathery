package com.example.weathery.home.data

import com.example.weathery.home.domain.model.Temperature
import com.example.weathery.home.domain.model.WeatherInformation
import com.example.weathery.home.domain.repository.WeatherRepository

class FakeRepository : WeatherRepository {
    var shouldError = false
    companion object {
        val weatherInformation = WeatherInformation(
            temperature = Temperature(
                current = 10.0,
                minimum = 5.0,
                maximum = 15.0
            ),
            windSpeed = 100.0,
            image = "image.com",
            weatherType = "rain",
            weatherDescription = "rainy",
            city = "BsAs"
        )
    }

    override suspend fun getWeatherInformation(city: String): Result<WeatherInformation> {
        if (shouldError) {
            return Result.failure(Throwable(""))
        }
        return Result.success(weatherInformation)
    }

    override suspend fun getWeatherByLatitudeAndLongitude(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInformation> {
        if (shouldError) {
            return Result.failure(Throwable(""))
        }
        return Result.success(weatherInformation)
    }
}