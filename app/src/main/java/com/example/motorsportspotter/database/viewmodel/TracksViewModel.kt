package com.example.motorsportspotter.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.database.entities.RemoteTrack
import com.example.motorsportspotter.database.entities.Track
import com.example.motorsportspotter.database.repositories.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TracksViewModel(private val repository: TracksRepository) : ViewModel() {

    val allTracks = repository.allTracks.asLiveData()
    val unfollowedTracks = repository.unfollowedTracks.asLiveData()
    val followedTracks = repository.followedTracks.asLiveData()

    fun insert(track : RemoteTrack) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(track)
        }
    }

    fun getTrack(id : Int) = repository.getTrackById(id).asLiveData()

    fun changeFollowed(id : Int) {
        viewModelScope.launch {
            repository.changeFollowed(id)
        }
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