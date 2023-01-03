package com.example.weathery.home.data.mapper

import com.example.weathery.home.data.remote.dto.WeatherInfoDto
import com.example.weathery.home.domain.model.Temperature
import com.example.weathery.home.domain.model.WeatherInformation

fun WeatherInfoDto.toDomain(): WeatherInformation {
    return WeatherInformation(
        temperature = Temperature(
            current = this.main.temp,
            maximum = this.main.temp_max,
            minimum = this.main.temp_min
        ),
        windSpeed = wind.speed,
        image = "https://openweathermap.org/img/wn/${this.weather.first().icon}@2x.png",
        weatherType = this.weather.first().main,
        weatherDescription = this.weather.first().description
    )
}
