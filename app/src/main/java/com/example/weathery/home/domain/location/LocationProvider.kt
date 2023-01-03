package com.example.weathery.home.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationProvider {
    suspend fun getLastLocation(): Location
}