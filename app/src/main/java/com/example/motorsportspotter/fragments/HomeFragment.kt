package com.example.motorsportspotter.fragments

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.motorsportspotter.R
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val locationHandler = { location : Location? ->
            val geocoder = Geocoder(requireContext(), Locale.ITALY)
            if (location != null) {
                val addresses: List<Address> =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)
                val locationText = view.findViewById<TextView>(R.id.location_text)
                locationText.text =
                    addresses.map { el -> el.toString() }.reduce { res, address -> res + address }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        fusedLocationClient.lastLocation.addOnSuccessListener(locationHandler)
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        fusedLocationClient.lastLocation.addOnSuccessListener(locationHandler)
                    }
                    else -> {
                        Toast.makeText(requireContext(), "no auth", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION))


        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener(locationHandler)
        }

    }


}
