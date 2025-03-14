package com.example.motorsportspotter.fragments.discover

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.adapters.EventCardAdapter
import com.example.motorsportspotter.adapters.EventCardsAdaptersFactory
import com.example.motorsportspotter.databinding.NearbyEventsFragmentBinding
import com.example.motorsportspotter.database.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.database.viewmodel.EventsViewModel
import com.example.motorsportspotter.database.viewmodel.EventsViewModelFactory
import com.example.motorsportspotter.models.EntitiesFilter
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.Task
import java.util.*


typealias LocationHandler = (Location) -> Unit

class NearbyEventsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var address : Address
    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }
    private lateinit var binding : NearbyEventsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = NearbyEventsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val handler = { location : Location ->
            val geocoder = Geocoder(requireContext(), Locale.ITALY)
            if(Build.VERSION.SDK_INT < 33) {
                val addresses: MutableList<Address>? =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)
                address = addresses!![0]
            }
            else{
                geocoder.getFromLocation(location.latitude, location.longitude, 1) { addresses ->
                    run {
                        if(addresses.isNotEmpty()){
                            address = addresses[0]
                        }
                    }
                }
            }
            val resultView = binding.nearbyEventsRw
            resultView.adapter = EventCardsAdaptersFactory.getEventCardAdapter()
            val adapter = binding.nearbyEventsRw.adapter as EventCardAdapter
            eventViewModel.allEvents.observe(viewLifecycleOwner) { events ->
                val filtered = EntitiesFilter.filterEventByRegion(DBEntitiesConvertersFactory.EventConverter.convertAll(events), address.countryCode)
                adapter.submitList(filtered)
            }
        }

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        getLocation(handler)
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        getLocation(handler)
                    }
                    else -> {
                        val geocoder = Geocoder(requireContext(), Locale.ITALY)
                        if(Build.VERSION.SDK_INT < 33) {
                            val addresses: MutableList<Address>? =
                                geocoder.getFromLocation(44.0217208, 12.4915422, 1)
                            address = addresses!![0]
                        }
                        else {
                            geocoder.getFromLocation(44.0217208, 12.4915422, 1) { addresses ->
                                run {
                                    if(addresses.isNotEmpty()){
                                        address = addresses[0]
                                    }
                                }
                            }
                        }
                    }
                }
            }

            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION))

        } else {
            getLocation(handler)
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLocation(handler: LocationHandler){
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(requireActivity(), 0x1)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationRes: LocationResult) {
                for (location in locationRes.locations){
                    if (location != null) {
                        handler(location)
                    }
                }
                fusedLocationClient.removeLocationUpdates(locationCallback)
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }


}
