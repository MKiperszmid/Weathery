package com.example.weathery.home.presentation.components

import android.R
import android.R.attr.contentDescription
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weathery.home.presentation.WeatherInfoState


@Composable
fun WeatherInfo(
    city: String,
    weatherInfoState: WeatherInfoState,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        when (weatherInfoState) {
            WeatherInfoState.Error -> {
                Text(text = "Error!")
            }
            is WeatherInfoState.Loaded -> {
                val information = weatherInfoState.information
                Column {
                    Text(text = "Text0")
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(information.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = information.weatherType,
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Text1")
                }
                Text(text = "Text2")
            }
            WeatherInfoState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}