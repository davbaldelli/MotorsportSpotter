package com.example.motorsportspotter.services.retrofit.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.services.retrofit.RemoteClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventsRepository {
    val events : MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>()
    }

    private val apiInterface = RemoteClient.getApiInterface()

    suspend fun fetchEvents() : Result<List<Event>>{
        val eventRes = apiInterface.allEvents().execute()
        eventRes.body()?.let {
            return Result.success(it)
        }
        return Result.failure(Throwable("couldn't fetch events"))
    }

}