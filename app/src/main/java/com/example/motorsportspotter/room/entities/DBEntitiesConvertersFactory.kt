package com.example.motorsportspotter.room.entities

import com.example.motorsportspotter.utilities.EntitiesConverter
import com.example.motorsportspotter.components.recyclerviews.Event as AdapterEvent

class DBEntitiesConvertersFactory {
    companion object {
        fun getEventsConverter() : EntitiesConverter<EventWithTrackAndChamp, AdapterEvent>{
            return EntitiesConverter { item ->
                AdapterEvent(
                    item.event.name,
                    item.event.date,
                    item.track.name!!,
                    item.event.image,
                    item.championship.name
                )
            }
        }
    }
}