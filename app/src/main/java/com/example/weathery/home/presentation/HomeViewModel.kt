package com.example.weathery.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathery.home.domain.usecase.GetWeatherByLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            supervisorScope {
                val jobs = state.cities.keys.map { city ->
                    updateCityInformation(city, WeatherInfoState.Loading)
                    async {
                        getWeatherByLocationUseCase(city).onSuccess {
                            updateCityInformation(city, WeatherInfoState.Loaded(it))
                        }.onFailure {
                            updateCityInformation(city, WeatherInfoState.Error)
                        }
                    }
                }
                jobs.awaitAll()
            }
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