package com.example.motorsportspotter.models

import android.content.Context

class EntitiesFilter {
    companion object{
        fun filterEventByRegion(events : List<Event>, region : String) : List<Event>{
            return events.filter { event ->
                event.track.nationCode == region
            }
        }
    }
}