package com.example.motorsportspotter.room.viewmodel

import androidx.lifecycle.*
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.entities.ChampionshipWithEvents
import com.example.motorsportspotter.room.repositories.ChampionshipRepository
import kotlinx.coroutines.launch

class ChampionshipsViewModel(private val repository: ChampionshipRepository): ViewModel() {
    val allChampionships : LiveData<List<ChampionshipWithEvents>> = repository.allChampionships.asLiveData()

    fun insert(championship: Championship) = viewModelScope.launch{
        repository.insert(championship)
    }

    fun getChampionshipById(id : Int) = repository.getChampionshipById(id)
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