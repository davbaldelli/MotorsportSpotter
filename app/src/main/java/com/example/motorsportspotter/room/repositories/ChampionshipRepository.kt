package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.entities.Championship
import kotlinx.coroutines.flow.Flow

class ChampionshipRepository(private val dao: ChampionshipDao) {
    val allChampionships : Flow<List<Championship>> = dao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(championship: Championship) {
        dao.insert(championship)
    }
}