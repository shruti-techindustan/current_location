package com.shruti.currentlocation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.google.android.gms.location.*


class CurrentLocationView : LinearLayout {
    var fusedLocationClient: FusedLocationProviderClient

    lateinit var locationRequest: LocationRequest
    private var locationCallback: LocationCallback
    lateinit var getCurrentLocation: GetCurrentLocation
    private var duration = 1000L as Long


    constructor(context: Context?) : super(context) {


        getCurrentLocation = context as GetCurrentLocation

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    Log.e("TAG", location.latitude.toString())
                    getCurrentLocation.getCurrentLocation(location)

                }
            }
        }
        locationRequestCreate()


    }


    fun locationRequestCreate() {
 locationRequest = LocationRequest()
        locationRequest.setInterval(duration)
        locationRequest.setFastestInterval(duration)
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
    }

    @SuppressLint("MissingPermission")
    public fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                null /* Looper */)
    }


    public fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    public interface GetCurrentLocation {
        fun getCurrentLocation(loc: Location)

    }

    fun setDuration(values: Long) {

        duration = values
        locationRequest.setInterval(duration)






       // locationRequestCreate()


    }

}




