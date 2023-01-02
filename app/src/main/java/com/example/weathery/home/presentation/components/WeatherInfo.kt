package com.example.weathery.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weathery.home.presentation.WeatherInfoState
import com.example.weathery.ui.theme.Background
import com.example.weathery.ui.theme.ErrorRed
import com.example.weathery.ui.theme.LightGray
import com.example.weathery.ui.theme.SuccessfulBlue


@Composable
fun WeatherInfo(
    city: String,
    weatherInfoState: WeatherInfoState,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (weatherInfoState) {
        WeatherInfoState.Error -> ErrorRed
        is WeatherInfoState.Loaded -> SuccessfulBlue
        WeatherInfoState.Loading -> LightGray
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(backgroundColor, shape = RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        when (weatherInfoState) {
            WeatherInfoState.Error -> {
                Text(text = "Error!")
            }
            is WeatherInfoState.Loaded -> {
                val information = weatherInfoState.information
                Text(
                    text = city, modifier = Modifier.align(CenterHorizontally),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
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
                            text = information.weatherType,
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Wind Speed: ${information.windSpeed}",
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 12.sp
                        )
                    }
                    TemperatureInformation(
                        currentTemperature = information.currentTemperature,
                        minimumTemperature = information.minimumTemperature,
                        maximumTemperature = information.maximumTemperature
                    )
                }
            }
            WeatherInfoState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }
        }
    }
}