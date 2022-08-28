package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.room.entities.Session
import com.example.motorsportspotter.room.repositories.SessionRepository
import kotlinx.coroutines.launch

class SessionViewModel(private val repository: SessionRepository) : ViewModel() {
    fun insert(session: Session) = viewModelScope.launch {
        repository.insert(session)
    }
}