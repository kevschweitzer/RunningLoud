package com.dev.kevinschweitzer.musicrunning.fragments

import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.kevinschweitzer.musicrunning.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.MapsInitializer
import android.Manifest.permission
import android.Manifest.permission.WRITE_CALENDAR
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat





class MapFragment: Fragment() {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.activity_map, container, false)

        mapView = rootView.findViewById(R.id.mapView) as MapView
        mapView.onCreate(savedInstanceState)

        mapView.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }


        mapView.getMapAsync{ mMap ->
            googleMap = mMap


            // For showing a move to my location button
            var permissionCheck = ContextCompat.checkSelfPermission(
                activity as Context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions( activity as Activity,  Array<String>(1) {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    1 )
            }
            permissionCheck = ContextCompat.checkSelfPermission(
                activity as Context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

            if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                googleMap.isMyLocationEnabled = true
            }

            // For dropping a marker at a point on the Map
            val sydney = LatLng(-34.0, 151.0)
            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"))

            // For zooming automatically to the location of the marker
            val cameraPosition = CameraPosition.Builder().target(sydney).zoom(12f).build()
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}