package com.example.motorsportspotter.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.motorsportspotter.database.entities.Session

@Dao
interface SessionDao {
    @Upsert
    suspend fun insert(session: Session)

    @Query("DELETE FROM sessions WHERE id NOT IN(:ids)")
    suspend fun deleteNotExistingSessions(ids : List<Int>)
}