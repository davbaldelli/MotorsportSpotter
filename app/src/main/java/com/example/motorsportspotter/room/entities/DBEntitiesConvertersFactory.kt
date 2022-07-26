package com.example.motorsportspotter.room.entities

import com.example.motorsportspotter.utilities.EntitiesConverter
import com.example.motorsportspotter.components.recyclerviews.entities.Championship as AdapterChampionship
import com.example.motorsportspotter.components.recyclerviews.entities.Event as AdapterEvent
import com.example.motorsportspotter.components.recyclerviews.entities.Track as AdapterTrack

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
                it.track.uid,
                it.event.image,
                it.championship.prettyName,
                it.championship.uid,
                location
            )
        }

        val ChampionshipEventConverter = EntitiesConverter<EventWithTrack, AdapterEvent>{
            AdapterEvent(
                it.name,
                it.startDate,
                it.endDate,
                it.trackName,
                null,
                it.image,
                null,
                null,
                null
            )
        }

        val TrackEventConverter = EntitiesConverter<EventWithChampionship, AdapterEvent>{
            AdapterEvent(
                it.name,
                it.startDate,
                it.endDate,
                null,
                null,
                it.image,
                it.championshipName,
                null,
                null
            )

        }

        val TracksConverter = EntitiesConverter<TrackWithEvents, AdapterTrack> {
            AdapterTrack(
                it.track.name,
                it.track.coordinates,
                it.track.image,
                it.track.logo
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