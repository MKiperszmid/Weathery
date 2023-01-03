package com.example.weathery.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weathery.home.domain.model.WeatherInformation

@Composable
fun WeatherDetails(
    information: WeatherInformation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(information.image)
                .crossfade(true)
                .build(),
            contentDescription = information.weatherType
        )
        Text(
            text = information.weatherDescription,
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
        Text(
            text = "Wind Speed: ${information.windSpeed}",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
    }
}