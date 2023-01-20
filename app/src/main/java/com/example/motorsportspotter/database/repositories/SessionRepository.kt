package com.example.motorsportspotter.database.repositories

import com.example.motorsportspotter.database.dao.SessionDao
import com.example.motorsportspotter.database.entities.Session

class SessionRepository(private val dao: SessionDao) {

    suspend fun insert(session: Session){
        dao.insert(session)
    }
}