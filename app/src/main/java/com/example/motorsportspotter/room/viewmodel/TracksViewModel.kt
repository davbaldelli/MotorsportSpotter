package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.*
import com.example.motorsportspotter.room.entities.Track
import com.example.motorsportspotter.room.repositories.TracksRepository
import kotlinx.coroutines.launch

class TracksViewModel(private val repository: TracksRepository) : ViewModel() {
    val allTracks : LiveData<List<Track>> = repository.allTracks.asLiveData()

    fun insert(track : Track) = viewModelScope.launch {
        repository.insert(track)
    }
}

class TracksViewModelFactory(private val repository: TracksRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TracksViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TracksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}