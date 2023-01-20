package com.example.motorsportspotter.database.repositories

import com.example.motorsportspotter.database.dao.EventDao
import com.example.motorsportspotter.database.entities.Event
import com.example.motorsportspotter.database.entities.EventWithTrackAndChamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepository(private val dao: EventDao) {

    val allEvents = dao.getAll()
    val incomingEvents  = dao.getIncomingEvents()

    val favouritesEvents = dao.getFavourites()

    suspend fun insert(event: Event) {
        dao.insert(event)
    }

    suspend fun setFavourite(id : Int) : Int{
        return withContext(Dispatchers.IO) {
            dao.setFavourite(id)
        }
    }

    suspend fun getFavSync() : List<EventWithTrackAndChamp>{
        return withContext(Dispatchers.IO) {
            dao.getFavouritesSync()
        }
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