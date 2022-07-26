package com.example.motorsportspotter.utilities

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import java.util.*

class EntitiesFilter {
    companion object{
        fun filterEventByRegion(events : List<Event>, region : String, context : Context) : List<Event>{
            return events.filter { event ->
                val geocoder = Geocoder(context, Locale.ITALY)
                val addresses: List<Address> =
                    geocoder.getFromLocation(event.trackLocation!!.first, event.trackLocation.second, 1)
                addresses[0].adminArea == region
            }

        }
    }
}