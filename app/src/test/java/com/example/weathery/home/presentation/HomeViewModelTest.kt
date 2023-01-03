package com.example.weathery.home.presentation

import android.location.Location
import com.example.weathery.CoroutineRule
import com.example.weathery.home.data.FakeRepository
import com.example.weathery.home.domain.location.LocationProvider
import com.example.weathery.home.domain.usecase.GetWeatherByCoordinatesUseCase
import com.example.weathery.home.domain.usecase.GetWeatherByLocationUseCase
import com.example.weathery.home.domain.usecase.HomeUseCases
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var viewmodel: HomeViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        val locationProvider = mockk<LocationProvider>(relaxed = true)
        viewmodel = HomeViewModel(
            HomeUseCases(
                GetWeatherByCoordinatesUseCase(repository),
                GetWeatherByLocationUseCase(repository)
            ),
            locationProvider
        )
        val location = mockk<Location>(relaxed = true)
        coEvery { locationProvider.getLastLocation() }.returns(location)
        every { location.latitude }.returns(123.0)
        every { location.longitude }.returns(456.0)
    }

    fun verifyInitialState(state: HomeState) {
        assertThat(state.currentLocation).isEqualTo(WeatherInfoState.Loading)
        state.cities.keys.forEach {
            assertThat(state.cities[it]).isEqualTo(WeatherInfoState.Loading)
        }
    }

    @Test
    fun `Get weather is valid, updates the state with information`() {
        verifyInitialState(viewmodel.state)
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.currentLocation).isEqualTo(WeatherInfoState.Loading)
        viewmodel.state.cities.keys.forEach {
            assertThat(viewmodel.state.cities[it]).isEqualTo(WeatherInfoState.Loaded(FakeRepository.weatherInformation))
        }
    }

    @Test
    fun `Get weather is error, updates the state with error`() {
        repository.shouldError = true
        verifyInitialState(viewmodel.state)
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.currentLocation).isEqualTo(WeatherInfoState.Loading)
        viewmodel.state.cities.keys.forEach {
            assertThat(viewmodel.state.cities[it]).isEqualTo(WeatherInfoState.Error)
        }
    }

    @Test
    fun `Get current location weather is valid, updates the state with information`() {
        verifyInitialState(viewmodel.state)
        assertThat(viewmodel.state.currentLocation).isEqualTo(WeatherInfoState.Loading)
        viewmodel.getWeatherFromCurrentLocation()
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.currentLocation).isEqualTo(WeatherInfoState.Loaded(FakeRepository.weatherInformation))
    }

    @Test
    fun `Get current location weather is error, updates the state with error`() {
        repository.shouldError = true
        verifyInitialState(viewmodel.state)
        assertThat(viewmodel.state.currentLocation).isEqualTo(WeatherInfoState.Loading)
        viewmodel.getWeatherFromCurrentLocation()
        coroutineRule.dispatchers.scheduler.advanceUntilIdle()
        assertThat(viewmodel.state.currentLocation).isEqualTo(WeatherInfoState.Error)
    }
}
