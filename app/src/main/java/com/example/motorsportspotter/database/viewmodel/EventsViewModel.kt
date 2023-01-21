package com.example.motorsportspotter.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.database.entities.Event
import com.example.motorsportspotter.database.repositories.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventRepository) : ViewModel() {

    val allEvents = repository.allEvents.asLiveData()
    val favouritesEvents = repository.favouritesEvents.asLiveData()
    val incomingEvents = repository.incomingEvents.asLiveData()

    fun insert(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    suspend fun setFavourite(id : Int) {
        repository.setFavourite(id)
    }


    fun futureChampionshipEvents(champId : Int) = repository.futureEventsByChamp(champId).asLiveData()
    fun ongoingChampionshipEvents(champId : Int) = repository.ongoingByChamp(champId).asLiveData()
    fun pastChampionshipEvents(champId : Int) = repository.pastEventsByChamp(champId).asLiveData()
    fun futureTrackEvents(trackId : Int) = repository.futureEventsByTrack(trackId).asLiveData()
    fun ongoingTrackEvents(trackId : Int) = repository.ongoingByTrack(trackId).asLiveData()
    fun pastTrackEvents(trackId : Int) = repository.pastEventsByTrack(trackId).asLiveData()
    fun getSimilarEvents(champId: Int, trackId : Int, eventId:Int) = repository.getSimilarEvents(champId, trackId, eventId).asLiveData()
    fun getById(id : Int) = repository.getEventById(id).asLiveData()
    fun getByIdSync(id : Int) = repository.getEventByIdSync(id)
}

class EventsViewModelFactory(private val repository: EventRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
