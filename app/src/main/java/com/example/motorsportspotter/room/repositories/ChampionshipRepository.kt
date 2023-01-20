package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.entities.Championship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChampionshipRepository(private val dao: ChampionshipDao) {
    val allChampionships = dao.getAll()
    val allUnfollowedChampionships = dao.getUnfollowed()
    val followedChampionships = dao.getFollowed()

    suspend fun insert(championship: Championship) {
        dao.insert(championship)
    }

    fun getChampionshipById(id : Int) = dao.getChampionshipWithEvents(id)

    suspend fun changeFollowed(id : Int) : Int {
        return withContext(Dispatchers.IO) {
            dao.changeFavourite(id)
        }
    }

}