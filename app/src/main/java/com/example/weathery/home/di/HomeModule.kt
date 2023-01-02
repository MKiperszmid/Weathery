package com.example.weathery.home.di

import com.example.weathery.home.data.WeatherRepositoryImpl
import com.example.weathery.home.data.remote.ApiInterceptor
import com.example.weathery.home.data.remote.WeatherApi
import com.example.weathery.home.domain.repository.WeatherRepository
import com.example.weathery.home.domain.usecase.GetWeatherByCoordinatesUseCase
import com.example.weathery.home.domain.usecase.GetWeatherByLocationUseCase
import com.example.weathery.home.domain.usecase.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeUseCases(repository: WeatherRepository): HomeUseCases {
        return HomeUseCases(
            getWeatherByCoordinates = GetWeatherByCoordinatesUseCase(repository),
            getWeatherByLocation = GetWeatherByLocationUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideOKhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).addInterceptor(ApiInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): WeatherApi {
        return Retrofit.Builder().baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create()
    }
}