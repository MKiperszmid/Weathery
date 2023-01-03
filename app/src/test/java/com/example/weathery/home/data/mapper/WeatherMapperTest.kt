package com.example.weathery.home.data.mapper

import com.example.weathery.home.data.remote.dto.Weather
import com.example.weathery.home.data.remote.dto.WeatherInfoDto
import com.example.weathery.home.domain.model.Temperature
import com.example.weathery.home.domain.model.WeatherInformation
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class WeatherMapperTest {

    private lateinit var weatherDto: WeatherInfoDto

    @Before
    fun setup() {
        weatherDto = mockk(relaxed = true)
        val weather = mockk<Weather>()

        every { weatherDto.wind.speed }.returns(123.0)
        every { weatherDto.main.temp }.returns(10.0)
        every { weatherDto.main.temp_max }.returns(15.0)
        every { weatherDto.main.temp_min }.returns(5.0)
        every { weatherDto.main.temp_min }.returns(5.0)
        every { weatherDto.weather }.returns(listOf(weather))
        every { weatherDto.name }.returns("city")
        every { weatherDto.weather.first().description }.returns("description")
        every { weatherDto.weather.first().icon }.returns("icon")
        every { weatherDto.weather.first().main }.returns("type")
    }

    @Test
    fun `Valid Dto returns valid Information`() {
        val result = weatherDto.toDomain()

        val expected = WeatherInformation(
            temperature = Temperature(
                current = 10.0,
                minimum = 5.0,
                maximum = 15.0
            ),
            windSpeed = 123.0,
            image = "https://openweathermap.org/img/wn/icon@2x.png",
            weatherType = "type",
            weatherDescription = "description",
            city = "city"
        )
        assertThat(result).isEqualTo(expected)
    }
}