package com.example.motorsportspotter.services.remote

import com.example.motorsportspotter.room.entities.Track as EntityTrack
import com.example.motorsportspotter.room.entities.Event as EntityEvent
import com.example.motorsportspotter.room.entities.Championship as ChampionshipEvent

class ConvertersFactory {
    companion object{
        fun getTrackConverter() : RemoteDataConverter<Track, EntityTrack> {
            return RemoteDataConverter{ item ->
                EntityTrack(
                    item.id,
                    item.coordinates,
                    item.name
                )
            }
        }

        fun getEventConverter() : RemoteDataConverter<Event, EntityEvent> {
            return RemoteDataConverter{ item ->
                EntityEvent(
                    item.id,
                    item.name,
                    item.trackId,
                    item.championshipId,
                    item.date,
                )
            }
        }

        fun getChampionshipConverter() : RemoteDataConverter<Championship, ChampionshipEvent> {
            return RemoteDataConverter{ item ->
                ChampionshipEvent(
                    item.id,
                    item.name,
                    item.year,
                )
            }
        }
    }
}