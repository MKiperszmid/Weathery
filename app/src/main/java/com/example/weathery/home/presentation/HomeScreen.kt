package com.example.weathery.home.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathery.home.presentation.components.WeatherInfo

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val requestPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.getWeatherFromCurrentLocation()
        }
    }

    LaunchedEffect(true) {
        requestPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }


    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            val extraName =
                if (state.currentLocation is WeatherInfoState.Loaded) " / ${state.currentLocation.information.city}" else ""
            WeatherInfo("Current Location$extraName", weatherInfoState = state.currentLocation,
                onRetry = {
                    viewModel.getWeatherFromCurrentLocation()
                })
        }
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
