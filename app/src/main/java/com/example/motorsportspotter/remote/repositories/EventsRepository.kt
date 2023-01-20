package com.example.motorsportspotter.remote.repositories

import androidx.lifecycle.MutableLiveData
import com.example.motorsportspotter.models.Event
import com.example.motorsportspotter.remote.RemoteClient

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