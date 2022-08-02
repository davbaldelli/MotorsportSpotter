package com.example.motorsportspotter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.ChampionshipWithEvents
import com.example.motorsportspotter.room.entities.Track
import com.example.motorsportspotter.room.entities.TrackWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert
    fun insert(track : Track)

    @Query("SELECT * FROM tracks")
    fun getAll() : Flow<List<TrackWithEvents>>

    @Query("SELECT * FROM tracks WHERE favourite = 0 LIMIT 10")
    fun getUnfollowed() : LiveData<List<TrackWithEvents>>

    @Query("SELECT * FROM tracks WHERE favourite = 1")
    fun getFollowed() : Flow<List<TrackWithEvents>>

    @Query("UPDATE tracks SET favourite = NOT favourite WHERE id = :id")
    fun changeFavourite(id : Int) : Int

    @Query("SELECT * FROM tracks WHERE id= :id")
    fun getTrackWithEvents(id : Int) : LiveData<TrackWithEvents>

}