package com.example.viewModel.ui.registrer

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.example.viewModel.common.MyApp
import com.example.viewModel.retrofit.models.LocationDetails
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


class LocationLiveData(application: Application): LiveData<LocationDetails>() {
    private val fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(application)

    override fun onActive() {
        super.onActive()
        if (ActivityCompat.checkSelfPermission(
                MyApp.instance,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MyApp.instance,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                location: Location? -> location.also {
            setLocationData(it)
        }
        }
        startLocationUpdate()
    }

    private fun startLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(MyApp.instance,Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyApp.instance, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null)
    }
    private val locationCallback = object :LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult?:return
            for (location in locationResult.locations){
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location?) {
value= LocationDetails(location?.longitude.toString(),location?.latitude.toString())


    }


    override fun onInactive() {
        super.onInactive()
fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
    companion object{
        val ONE_MINUTE:Long =60000
        val ONE_HOUR:Long = ONE_MINUTE * 60

    }
        val locationRequest: LocationRequest= LocationRequest.create().apply {
            interval = 60000
            fastestInterval= ONE_MINUTE/4
            priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        }
}