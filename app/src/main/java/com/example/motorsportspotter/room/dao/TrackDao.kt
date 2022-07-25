package com.example.motorsportspotter.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Track
import com.example.motorsportspotter.room.entities.TrackWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert
    fun insert(track : Track)

    @Query("SELECT * FROM tracks")
    fun getAll() : Flow<List<Track>>

    @Query("SELECT * FROM tracks WHERE id= :id")
    fun getTrackWithEvents(id : Int) : TrackWithEvents

}