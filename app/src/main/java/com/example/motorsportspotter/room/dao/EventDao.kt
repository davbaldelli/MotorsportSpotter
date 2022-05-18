package com.example.motorsportspotter.room.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp

interface EventDao {
    @Insert
    fun insertTrack(event: Event)

    @Query("SELECT * FROM events")
    fun getAll() : List<EventWithTrackAndChamp>
}