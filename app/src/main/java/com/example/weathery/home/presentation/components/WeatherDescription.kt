package com.example.weathery.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weathery.home.domain.model.WeatherInformation

@Composable
fun WeatherDescription(
    information: WeatherInformation,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        WeatherDetails(information)
        TemperatureInformation(
            currentTemperature = information.currentTemperature,
            minimumTemperature = information.minimumTemperature,
            maximumTemperature = information.maximumTemperature
        )
    }

}