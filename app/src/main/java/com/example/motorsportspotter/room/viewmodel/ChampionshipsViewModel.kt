package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.repositories.ChampionshipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChampionshipsViewModel(private val repository: ChampionshipRepository): ViewModel() {
    val allChampionships = repository.allChampionships.asLiveData()
    val allUnfollowedChampionships = repository.allUnfollowedChampionships.asLiveData()
    val followedChampionships = repository.followedChampionships.asLiveData()

    fun insert(championship: Championship) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(championship)
        }
    }

    fun changeFollowed(id : Int) {
        viewModelScope.launch{
            val result = repository.changeFollowed(id)
        }
    }


    fun getChampionshipById(id : Int) = repository.getChampionshipById(id).asLiveData()
}


class ChampionshipsViewModelFactory(private val repository: ChampionshipRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChampionshipsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ChampionshipsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}