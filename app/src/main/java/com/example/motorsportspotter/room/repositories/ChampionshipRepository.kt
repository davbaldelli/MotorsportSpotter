package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.entities.ChampionshipWithEvents
import kotlinx.coroutines.flow.Flow

class ChampionshipRepository(private val dao: ChampionshipDao) {
    val allChampionships : Flow<List<ChampionshipWithEvents>> = dao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(championship: Championship) {
        dao.insert(championship)
    }

    fun getChampionshipById(id : Int) = dao.getChampionshipWithEvents(id)
}