package com.example.weathery.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TemperatureInformation(
    currentTemperature: Double,
    minimumTemperature: Double,
    maximumTemperature: Double,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        // ToInt so we don't display decimals
        Text(
            text = "${currentTemperature.toInt()}°",
            color = Color.White,
            fontSize = 40.sp
        )
        Text(
            text = "Min: ${minimumTemperature.toInt()}°",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
        Text(
            text = "Max: ${maximumTemperature.toInt()}°",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
    }
}