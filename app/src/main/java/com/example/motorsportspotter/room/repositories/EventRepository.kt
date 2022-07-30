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

    fun setFavourite(id : Int){
        dao.setCarFavourite(id)
    }



    fun futureEventsByChamp(champId : Int) = dao.getFutureFromChampionship(champId)
    fun ongoingByChamp(champId : Int) = dao.getOngoingFromChampionship(champId)
    fun pastEventsByChamp(champId : Int) = dao.getPastFromChampionship(champId)

    fun futureEventsByTrack(trackId: Int) = dao.getFutureFromTrack(trackId)
    fun ongoingByTrack(trackId : Int) = dao.getOngoingFromTrack(trackId)
    fun pastEventsByTrack(trackId : Int) = dao.getPastFromTrack(trackId)

    fun getSimilarEvents(champId: Int, trackId : Int, eventId:Int) = dao.getSimilarEvents(champId, trackId, eventId)

    fun getEventById(id : Int) = dao.getById(id)

}