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
                event.track.nationCode == region
            }
        }

        fun filterEventsByCountry(events : List<Event>, country : String, context : Context) : List<Event>{
            return events.filter { event ->
                event.track.coordinates?.let {
                    val geocoder = Geocoder(context, Locale.ITALY)
                    val addresses: List<Address> =
                        geocoder.getFromLocation(event.track.coordinates.first.toDouble(), event.track.coordinates.second.toDouble(), 1)
                    addresses[0].countryCode == country
                } == true
            }

        }
    }
}