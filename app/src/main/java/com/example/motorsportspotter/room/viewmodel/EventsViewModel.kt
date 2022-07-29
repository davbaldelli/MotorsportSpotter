package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.*
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp
import com.example.motorsportspotter.room.repositories.EventRepository
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventRepository) : ViewModel() {

    val allEvents = repository.allEvents.asLiveData()
    val favouritesEvents = repository.favouritesEvents.asLiveData()

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun getByChampionship(champId : Int) = repository.eventsByChamp(champId)

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
