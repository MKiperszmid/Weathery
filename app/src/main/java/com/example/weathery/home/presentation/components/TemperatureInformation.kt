package com.example.weathery.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.weathery.home.domain.model.Temperature

@Composable
fun TemperatureInformation(
    temperature: Temperature,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        // ToInt so we don't display decimals
        Text(
            text = "${temperature.current.toInt()}°",
            color = Color.White,
            fontSize = 40.sp
        )
        Text(
            text = "Min: ${temperature.minimum.toInt()}°",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
        Text(
            text = "Max: ${temperature.maximum.toInt()}°",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
    }
}