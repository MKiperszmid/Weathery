package com.example.weathery.home.di

import com.example.weathery.home.data.WeatherRepositoryImpl
import com.example.weathery.home.domain.repository.WeatherRepository
import com.example.weathery.home.domain.usecase.GetWeatherByLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeUseCases(repository: WeatherRepository): GetWeatherByLocationUseCase {
        return GetWeatherByLocationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(): WeatherRepository {
        return WeatherRepositoryImpl()
    }
}