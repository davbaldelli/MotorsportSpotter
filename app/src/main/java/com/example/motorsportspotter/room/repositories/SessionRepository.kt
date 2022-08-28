package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import androidx.room.Dao
import com.example.motorsportspotter.room.dao.SessionDao
import com.example.motorsportspotter.room.entities.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionRepository(private val dao: SessionDao) {
    @WorkerThread
    suspend fun insert(session: Session){
        withContext(Dispatchers.IO){
            dao.insert(session)
        }
    }
}