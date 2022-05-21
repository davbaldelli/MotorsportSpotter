package com.example.motorsportspotter.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert
    fun insert(event: Event)

    @Query("SELECT * FROM events")
    fun getAll() : Flow<List<EventWithTrackAndChamp>>
}