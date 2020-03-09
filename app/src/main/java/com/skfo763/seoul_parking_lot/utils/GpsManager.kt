package com.skfo763.seoul_parking_lot.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder

class GpsManager(private val context: Context): Service(), LocationListener {

    companion object {
        private const val MIN_UPDATE_TIME: Long = 1000*60*1
        private const val MIN_UPDATE_DIST: Float = 10.0F
    }

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    override fun onBind(intent: Intent?): IBinder? = null

    fun requestLocationWithPermission(onPermissionSuccess: (Location?) -> Unit,
                                      onPermissionFailure: (permissions: List<String>) -> Unit) {
        val notGrantedPermission = PermissionProcessor.checkPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if(notGrantedPermission.isEmpty()) {
            onPermissionSuccess.invoke(retrieveDataWithLocationManager())
        } else {
            onPermissionFailure.invoke(notGrantedPermission)
        }
    }

    private fun retrieveDataWithLocationManager(): Location? {
        return when {
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> {
                requestLocation(LocationManager.NETWORK_PROVIDER)
            }
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> {
                requestLocation(LocationManager.GPS_PROVIDER)
            }
            else -> null
        }
    }

    // getLocation 함수에서 권한체크를 하므로, 이 함수는 절대로 직접 접근하면 안됩니다.
    @SuppressLint("MissingPermission")
    private fun requestLocation(provider: String): Location? {
        locationManager.requestLocationUpdates(provider, MIN_UPDATE_TIME, MIN_UPDATE_DIST, this)
        return locationManager.getLastKnownLocation(provider)
    }

    override fun onLocationChanged(location: Location?) {}

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

    fun stopGps() {
        locationManager.removeUpdates(this)
    }
}