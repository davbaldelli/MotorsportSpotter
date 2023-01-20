package com.example.motorsportspotter.services.retrofit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.services.retrofit.repositories.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {

    val events : MutableLiveData<List<Event>> by lazy {
        MutableLiveData()
    }
    private val repository = EventsRepository()

    fun fetchEvents(){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.fetchEvents()
            events.postValue(result.getOrThrow())
        }
    }


}