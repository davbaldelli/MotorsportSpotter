package com.example.motorsportspotter.database.entities

import com.example.motorsportspotter.models.EntitiesConverter
import java.time.LocalDate
import com.example.motorsportspotter.models.Championship as AdapterChampionship
import com.example.motorsportspotter.models.Event as AdapterEvent
import com.example.motorsportspotter.models.Track as AdapterTrack
import com.example.motorsportspotter.models.Session as AdapterSession

class DBEntitiesConvertersFactory {
    companion object {

        val EventConverter = EntitiesConverter<EventWithTrackAndChamp, AdapterEvent> {
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
            AdapterSession(
                it.id,
                it.name,
                it.date,
                it.time,
                it.timezone,
                it.durationMin,
                it.durationLaps,
            )
        }

        val TracksConverter = EntitiesConverter<Track, AdapterTrack> {
            AdapterTrack(
                it.id,
                it.name,
                it.image,
                it.logo,
                it.favourite,
                it.locationName,
                it.nationCode
            )
        }


        val ChampionshipsConverter = EntitiesConverter<Championship, AdapterChampionship>{
            AdapterChampionship(
                it.id,
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