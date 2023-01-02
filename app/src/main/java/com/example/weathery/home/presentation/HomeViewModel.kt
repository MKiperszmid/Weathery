package com.example.weathery.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathery.home.domain.usecase.GetWeatherByLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            getWeatherByLocationUseCase(34.9011, 56.1645).onSuccess {
                println()
            }.onFailure {
                println()
            }
        }
    }
}