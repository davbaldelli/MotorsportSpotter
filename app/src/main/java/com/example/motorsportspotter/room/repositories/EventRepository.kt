package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.EventDao
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp
import kotlinx.coroutines.flow.Flow

class EventRepository(private val dao: EventDao) {

    val allEvents : Flow<List<EventWithTrackAndChamp>> = dao.getAll()

    val favouritesEvents = dao.getFavourites()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(event: Event) {
        dao.insert(event)
    }

    fun eventsByChamp(champId : Int) = dao.getAllFromChampionship(champId)

    fun getSimilarEvents(champId: Int, trackId : Int, eventId:Int) = dao.getSimilarEvents(champId, trackId, eventId)

    fun getEventById(id : Int) = dao.getById(id)

}