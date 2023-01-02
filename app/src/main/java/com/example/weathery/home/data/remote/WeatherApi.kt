package com.example.weathery.home.data.remote

import com.example.weathery.home.data.remote.dto.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("weather?units=metric")
    suspend fun getWeatherByLocation(
        @Query("q") city: String
    ): WeatherInfoDto
}