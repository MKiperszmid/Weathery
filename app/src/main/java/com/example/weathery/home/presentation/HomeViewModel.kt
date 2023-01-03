package com.example.weathery.home.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathery.home.domain.location.LocationProvider
import com.example.weathery.home.domain.usecase.HomeUseCases
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases,
    private val locationProvider: LocationProvider
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            state.cities.keys.map { city ->
                async {
                    getWeatherByCity(city)
                }
            }
        }
    }

    private suspend fun getWeatherByCity(city: String) {
        updateCityInformation(city, WeatherInfoState.Loading)
        useCases.getWeatherByLocation(city).onSuccess {
            updateCityInformation(city, WeatherInfoState.Loaded(it))
        }.onFailure {
            updateCityInformation(city, WeatherInfoState.Error)
        }
    }

    fun getWeatherFromCurrentLocation() {
        viewModelScope.launch {
            try {
                state = state.copy(
                    currentLocation = WeatherInfoState.Loading
                )
                val location = locationProvider.getLastLocation()
                useCases.getWeatherByCoordinates(
                    latitude = location.latitude,
                    longitude = location.longitude
                ).onSuccess {
                    state = state.copy(
                        currentLocation = WeatherInfoState.Loaded(it)
                    )
                }.onFailure {
                    state = state.copy(
                        currentLocation = WeatherInfoState.Error
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    currentLocation = WeatherInfoState.Error
                )
            }
        }
    }

    fun retry(city: String) {
        viewModelScope.launch {
            getWeatherByCity(city)
        }
    }

    private fun updateCityInformation(city: String, information: WeatherInfoState) {
        val map = state.cities.toMutableMap()
        map[city] = information
        state = state.copy(
            cities = map
        )
    }
}