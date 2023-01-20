package com.example.motorsportspotter.utilities

import android.content.Context
import com.example.motorsportspotter.models.Event

class EntitiesFilter {
    companion object{
        fun filterEventByRegion(events : List<Event>, region : String, context : Context) : List<Event>{
            return events.filter { event ->
                event.track.nationCode == region
            }
        }
    }
}