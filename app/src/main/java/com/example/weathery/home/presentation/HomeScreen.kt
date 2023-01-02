package com.example.weathery.home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathery.home.presentation.components.WeatherInfo

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        state.cities.keys.map { city ->
            item {
                val weatherInfoState = state.cities[city] ?: return@item
                WeatherInfo(city, weatherInfoState = weatherInfoState, onRetry = {
                    viewModel.retry(city)
                })
            }
        }
    }

}