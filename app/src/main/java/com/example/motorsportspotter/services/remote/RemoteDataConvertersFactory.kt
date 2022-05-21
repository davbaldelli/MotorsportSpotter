package com.example.motorsportspotter.services.remote


import com.example.motorsportspotter.utilities.EntitiesConverter
import com.example.motorsportspotter.room.entities.Track as EntityTrack
import com.example.motorsportspotter.room.entities.Event as EntityEvent
import com.example.motorsportspotter.room.entities.Championship as ChampionshipEvent

class RemoteDataConvertersFactory {
    companion object {
        fun getTrackConverter(): EntitiesConverter<Track, EntityTrack> {
            return EntitiesConverter { item: Track ->
                EntityTrack(
                    item.id,
                    item.coordinates,
                    item.name
                )
            }
        }

        fun getEventConverter(): EntitiesConverter<Event, EntityEvent> {
            return EntitiesConverter { item ->
                EntityEvent(
                    item.id,
                    item.name,
                    item.trackId,
                    item.championshipId,
                    item.date.toString(),
                    item.image,
                )
            }
        }

        fun getChampionshipConverter(): EntitiesConverter<Championship, ChampionshipEvent> {
            return EntitiesConverter { item ->
                ChampionshipEvent(
                    item.id,
                    item.name,
                    item.year,
                )
            }
        }
    }
}