package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.room.entities.Session
import com.example.motorsportspotter.room.repositories.ChampionshipRepository
import com.example.motorsportspotter.room.repositories.SessionRepository
import kotlinx.coroutines.launch

class SessionViewModel(val repository: SessionRepository) : ViewModel() {
    fun insert(session: Session) = viewModelScope.launch {
        repository.insert(session)
    }
}

class SessionViewModelFactory(private val repository: SessionRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SessionViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SessionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}