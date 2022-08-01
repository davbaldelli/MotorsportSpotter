package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.*
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.repositories.EventRepository
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventRepository) : ViewModel() {

    val allEvents = repository.allEvents.asLiveData()
    val favouritesEvents = repository.favouritesEvents.asLiveData()
    val incomingEvents = repository.incomingEvents.asLiveData()

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun setFavourite(id : Int) {
        repository.setFavourite(id)
    }

    fun futureChampionshipEvents(champId : Int) = repository.futureEventsByChamp(champId)
    fun ongoingChampionshipEvents(champId : Int) = repository.ongoingByChamp(champId)
    fun pastChampionshipEvents(champId : Int) = repository.pastEventsByChamp(champId)

    fun futureTrackEvents(trackId : Int) = repository.futureEventsByTrack(trackId)
    fun ongoingTrackEvents(trackId : Int) = repository.ongoingByTrack(trackId)
    fun pastTrackEvents(trackId : Int) = repository.pastEventsByTrack(trackId)

    fun getSimilarEvents(champId: Int, trackId : Int, eventId:Int) = repository.getSimilarEvents(champId, trackId, eventId)

    fun getById(id : Int) = repository.getEventById(id)
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
