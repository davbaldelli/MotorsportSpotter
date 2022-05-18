package com.example.motorsportspotter.room.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Track
import com.example.motorsportspotter.room.entities.TrackWithEvents

interface TrackDao {
    @Insert
    fun insertTrack(track : Track)

    @Query("SELECT * FROM tracks")
    fun getAll() : List<Track>

    @Query("SELECT * FROM tracks WHERE id= :id")
    fun getTrackWithEvents(id : Int) : TrackWithEvents
}