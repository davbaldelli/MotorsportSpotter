package com.example.motorsportspotter.database.repositories

import com.example.motorsportspotter.database.dao.ChampionshipDao
import com.example.motorsportspotter.database.entities.Championship
import com.example.motorsportspotter.database.entities.RemoteChampionship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChampionshipRepository(private val dao: ChampionshipDao) {
    val allChampionships = dao.getAll()
    val allUnfollowedChampionships = dao.getUnfollowed()
    val followedChampionships = dao.getFollowed()

    suspend fun insert(championship: RemoteChampionship) {
        withContext(Dispatchers.IO){
            dao.insert(championship)
        }
    }

    fun getChampionshipById(id : Int) = dao.getChampionshipWithEvents(id)

    suspend fun changeFollowed(id : Int) : Int {
        return withContext(Dispatchers.IO) {
            dao.changeFavourite(id)
        }
    }

    suspend fun deleteNotExistingChampionships(ids : List<Int>) {
        withContext(Dispatchers.IO) {
            dao.deleteNotExistingChampionships(ids)
        }
    }

}