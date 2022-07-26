package com.example.motorsportspotter.fragments.championship

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChampionshipFragmentViewModel :ViewModel() {
    var championshipId = MutableLiveData<Int>()

    fun setChampionshipId(id : Int){
        championshipId.value = id
    }
}