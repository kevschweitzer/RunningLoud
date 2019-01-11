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
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.PlaceDetectionClient
import com.google.android.gms.location.places.Places


class MapFragment: Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var mView: View
    private lateinit var mGeoDataClient: GeoDataClient
    private lateinit var mPlaceDetectionClient: PlaceDetectionClient
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(activity as Activity, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(activity as Activity, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity as Activity);
        mView = inflater.inflate(R.layout.fragment_map, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = mView.findViewById(R.id.mapView)
        mapView.let{
            mapView.onCreate(null)
            mapView.onResume()
            mapView.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        this.googleMap = googleMap

        if ( ContextCompat.checkSelfPermission( activity as Activity, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( activity as Activity, Array<String>(1) {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                1 );
        }

        googleMap.isMyLocationEnabled = true

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }





}