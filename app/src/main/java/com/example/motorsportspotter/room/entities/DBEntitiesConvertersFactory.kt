package com.example.motorsportspotter.room.entities

import com.example.motorsportspotter.utilities.EntitiesConverter
import com.example.motorsportspotter.components.recyclerviews.entities.Event as AdapterEvent
import com.example.motorsportspotter.components.recyclerviews.entities.Track as AdapterTrack
import com.example.motorsportspotter.components.recyclerviews.entities.Championship as AdapterChampionship

class DBEntitiesConvertersFactory {
    companion object {
        fun getEventsConverter() : EntitiesConverter<EventWithTrackAndChamp, AdapterEvent>{
            return EntitiesConverter {
                AdapterEvent(
                    it.event.name,
                    it.event.date,
                    it.track.name,
                    it.event.image,
                    it.championship.name
                )
            }
        }

        fun getTracksConverter() : EntitiesConverter<Track, AdapterTrack>{
            return EntitiesConverter { AdapterTrack(
                it.name,
                it.coordinates
            ) }
        }

        fun getChampionshipsConverter() : EntitiesConverter<Championship, AdapterChampionship>{
            return EntitiesConverter {
                AdapterChampionship(
                    it.name,
                    it.year
                )
            }
        }
    }
}