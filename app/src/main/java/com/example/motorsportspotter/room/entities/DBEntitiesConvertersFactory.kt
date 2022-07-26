package com.example.motorsportspotter.room.entities

import com.example.motorsportspotter.utilities.EntitiesConverter
import com.example.motorsportspotter.components.recyclerviews.entities.Event as AdapterEvent
import com.example.motorsportspotter.components.recyclerviews.entities.Track as AdapterTrack
import com.example.motorsportspotter.components.recyclerviews.entities.Championship as AdapterChampionship

class DBEntitiesConvertersFactory {
    companion object {

        val CompleteEventConverter = EntitiesConverter<EventWithTrackAndChamp, AdapterEvent> {
            val coordinates = it.track.coordinates.split(',')
            val location = Pair(coordinates[0].toDouble(),coordinates[1].toDouble())
            AdapterEvent(
                it.event.name,
                it.event.startDate,
                it.event.endDate,
                it.track.name,
                it.event.image,
                it.championship.prettyName,
                location
            )
        }

        val ChampionshipEventConverter = EntitiesConverter<EventWithTrack, AdapterEvent>{
            AdapterEvent(
                it.name,
                it.startDate,
                it.endDate,
                it.trackName,
                it.image,
                null,
                null
            )
        }

        val TracksConverter = EntitiesConverter<Track, AdapterTrack> {
            AdapterTrack(
                it.name,
                it.coordinates
            )
        }

        val ChampionshipsConverter = EntitiesConverter<ChampionshipWithEvents, AdapterChampionship>{
            AdapterChampionship(
                it.championship.name,
                it.championship.year,
                it.championship.prettyName,
                it.championship.image,
                it.championship.logo
            )
        }
    }
}