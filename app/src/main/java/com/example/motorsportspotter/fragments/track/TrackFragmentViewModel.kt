package com.example.motorsportspotter.fragments.track

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrackFragmentViewModel : ViewModel() {
    var trackId = MutableLiveData<Int>()

    fun setTrackId(id : Int){
        trackId.value = id
    }

}