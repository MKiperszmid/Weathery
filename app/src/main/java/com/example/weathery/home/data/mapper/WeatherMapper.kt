package com.example.weathery.home.data.mapper

import com.example.weathery.home.data.remote.dto.WeatherInfoDto
import com.example.weathery.home.domain.model.WeatherInformation

fun WeatherInfoDto.toDomain(): WeatherInformation {
    return WeatherInformation(
        currentTemperature = this.main.temp,
        minimumTemperature = this.main.temp_min,
        maximumTemperature = this.main.temp_max,
        windSpeed = wind.speed,
        image = "https://openweathermap.org/img/wn/${this.weather.first().icon}@2x.png",
        longitude = this.coord.lon,
        latitude = this.coord.lat,
        weatherType = this.weather.first().main
    )
}
