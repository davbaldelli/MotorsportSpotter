package com.example.motorsportspotter.fragments.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventDetailFragmentViewModel :ViewModel() {
    val eventId = MutableLiveData<Int>()

    fun setEventId(id : Int) {
        eventId.value = id
    }

}