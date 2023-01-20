package com.example.motorsportspotter.room.entities

import com.example.motorsportspotter.utilities.EntitiesConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.example.motorsportspotter.components.recyclerviews.entities.Championship as AdapterChampionship
import com.example.motorsportspotter.components.recyclerviews.entities.Event as AdapterEvent
import com.example.motorsportspotter.components.recyclerviews.entities.Track as AdapterTrack
import com.example.motorsportspotter.components.recyclerviews.entities.Session as AdapterSession
import com.example.motorsportspotter.components.recyclerviews.entities.News as AdapterNews

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

        val NewsConverter = EntitiesConverter<News, AdapterNews>{
            AdapterNews(
                it.id,
                it.title,
                it.subtitle,
                it.content,
                it.author,
                LocalDate.parse(it.date),
                it.image
            )
        }

    }
}