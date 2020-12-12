package com.example.viewmodel.ui.maps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.viewmodel.R
import com.example.viewmodel.common.MyApp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import java.util.EnumSet.of

class MapsFragment : Fragment(),OnMapReadyCallback {

    private lateinit var mapsViewModel: MapsViewModel
    private lateinit var mMap:GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            mapsViewModel = ViewModelProviders.of(it!!).get(MapsViewModel::class.java)
        }

        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap?.let {
            mMap = it
            createMarker()
        }

    }

    private fun createMarker() {
        val lat = 19.3879161
       val long = -99.1042885
        val coordenate = LatLng(lat, long)
        val marker:MarkerOptions = MarkerOptions().position(coordenate).title("aldairo")
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenate, 18f),
                4000,
                null
        )

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_maps, container, false)
        val mapFragment= childFragmentManager.findFragmentById(R.id.navigation_notifications) as SupportMapFragment
        mapFragment.getMapAsync { 
            googleMap ->  mMap=googleMap
        }
        //val textView: TextView = root.findViewById(R.id.text_notifications)
        mapsViewModel.text.observe(viewLifecycleOwner, Observer {
            //  textView.text = it
            Log.i("LOVE VIRGENCITA",mMap.toString())

        })
        return root
    }




}