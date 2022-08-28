package com.example.motorsportspotter.room.entities

import com.example.motorsportspotter.utilities.EntitiesConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.example.motorsportspotter.components.recyclerviews.entities.Championship as AdapterChampionship
import com.example.motorsportspotter.components.recyclerviews.entities.Event as AdapterEvent
import com.example.motorsportspotter.components.recyclerviews.entities.Track as AdapterTrack
import com.example.motorsportspotter.components.recyclerviews.entities.Session as AdapterSession

class DBEntitiesConvertersFactory {
    companion object {

        val CompleteEventConverter = EntitiesConverter<EventWithTrackAndChamp, AdapterEvent> {
            AdapterEvent(
                it.event.id,
                it.event.name,
                LocalDate.parse(it.event.startDate),
                LocalDate.parse(it.event.endDate),
                it.event.image,
                TracksConverter.convertAll(listOf(it.track))[0],
                ChampionshipsConverter.convertAll(listOf(it.championship))[0],
                SessionConverter.convertAll(it.sessions),
            )
        }

        val SessionConverter = EntitiesConverter<Session, AdapterSession> {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            AdapterSession(
                it.id,
                it.name,
                LocalDateTime.parse(it.datetime, formatter),
                it.durationMin,
                it.durationLaps,
            )
        }

        val TracksConverter = EntitiesConverter<Track, AdapterTrack> {
            val coordinates = it.coordinates.split(",")
            AdapterTrack(
                it.uid,
                it.name,
                //Pair(coordinates[0], coordinates[1]),
                null,
                it.image,
                it.logo,
                it.favourite,
                it.locationName
            )
        }

        val ChampionshipsConverter = EntitiesConverter<Championship, AdapterChampionship>{
            AdapterChampionship(
                it.uid,
                it.name,
                it.year,
                it.prettyName,
                it.image,
                it.logo,
                it.favourite,
                it.liveStreamLink,
            )
        }

    }
}