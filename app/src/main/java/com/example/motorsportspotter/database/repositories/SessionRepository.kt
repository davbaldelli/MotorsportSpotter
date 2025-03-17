package com.example.motorsportspotter.database.repositories

import com.example.motorsportspotter.database.dao.SessionDao
import com.example.motorsportspotter.database.entities.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionRepository(private val dao: SessionDao) {

    suspend fun insert(session: Session){
        withContext(Dispatchers.IO) {
            dao.insert(session)
        }
    }
    suspend fun deleteNotExistingSessions(ids : List<Int>){
        withContext(Dispatchers.IO) {
            dao.deleteNotExistingSessions(ids)
        }
    }

}