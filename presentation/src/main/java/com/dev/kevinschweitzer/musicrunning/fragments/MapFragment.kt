package com.dev.kevinschweitzer.musicrunning.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.kevinschweitzer.musicrunning.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.view.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng




class MapFragment: Fragment(), OnMapReadyCallback{

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var inflatedView: View
    private val LOCATION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: Location? = null
    private val CITY_ZOOM: Float = 15f
    private lateinit var locationCallback: LocationCallback


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflatedView = inflater.inflate(R.layout.fragment_map, container, false)
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = inflatedView.mapView
        mapView.let{
            mapView.onCreate(null)
            mapView.onResume()
            mapView.getMapAsync(this)
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    val current = LatLng(location.latitude, location.longitude)
                    googleMap.addMarker(MarkerOptions().position(current))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current,CITY_ZOOM))
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(gmap: GoogleMap) {
        googleMap = gmap
        checkForPermissions()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as Activity)
        if(googleMap.isMyLocationEnabled){
            fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location? ->
                        currentLocation = location
                        location?.let {
                            Log.i("Location Lat Long", it.latitude.toString() + " " +  it.longitude.toString())
                            val current = LatLng(it.latitude, it.longitude)
                            googleMap.addMarker(MarkerOptions().position(current))
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current,CITY_ZOOM))
                        }
                    }
        }
        fusedLocationClient.requestLocationUpdates(createLocationRequest(), locationCallback, null)

    }

    fun createLocationRequest(): LocationRequest? {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        return locationRequest
    }

    private fun checkForPermissions() {
        if (ActivityCompat.checkSelfPermission(activity as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity as Activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
          googleMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(activity as Activity, Array(1) { android.Manifest.permission.ACCESS_FINE_LOCATION }, LOCATION_REQUEST_CODE)
            ActivityCompat.requestPermissions(activity as Activity, Array(1) { android.Manifest.permission.ACCESS_COARSE_LOCATION }, LOCATION_REQUEST_CODE)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if(permissions.size == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true
            }
        } else {
            Log.i("Permissions","Don't have permissions")
        }
    }

}
