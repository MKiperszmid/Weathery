package com.example.weathery.home.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.weathery.home.domain.location.LocationProvider
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocationProviderImpl(
    private val context: Context
) : LocationProvider {
    override suspend fun getLastLocation(): Location {
        return suspendCoroutine { continuation ->
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                continuation.resumeWithException(RuntimeException("Location permission is required"))
            }

            val locationProvider = LocationServices.getFusedLocationProviderClient(context)
            locationProvider.lastLocation.addOnSuccessListener { location ->
                if (location == null) {
                    continuation.resumeWithException(RuntimeException("Error getting location"))
                }
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resumeWithException(RuntimeException("Error getting location"))
            }
        }
    }
}
