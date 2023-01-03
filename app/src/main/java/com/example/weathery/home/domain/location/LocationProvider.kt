package com.example.weathery.home.domain.location

import android.location.Location

interface LocationProvider {
    suspend fun getLastLocation(): Location
}