package com.example.motorsportspotter.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.motorsportspotter.room.entities.Session

@Dao
interface SessionDao {
    @Insert
    fun insert(session: Session)
}