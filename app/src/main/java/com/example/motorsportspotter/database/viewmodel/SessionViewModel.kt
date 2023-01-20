package com.example.motorsportspotter.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.database.entities.Session
import com.example.motorsportspotter.database.repositories.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SessionViewModel(private val repository: SessionRepository) : ViewModel() {
    fun insert(session: Session) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(session)
        }
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