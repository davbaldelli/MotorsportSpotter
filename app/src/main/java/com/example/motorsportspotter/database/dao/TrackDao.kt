package com.example.motorsportspotter.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.motorsportspotter.database.entities.RemoteTrack
import com.example.motorsportspotter.database.entities.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Upsert(entity = Track::class)
    suspend fun insert(track : RemoteTrack)

    @Query("DELETE FROM tracks WHERE id NOT IN(:ids)")
    fun clearNotExistingTracks(ids : List<Int>)

    @Query("SELECT * FROM tracks")
    fun getAll() : Flow<List<Track>>

    @Query("SELECT * FROM tracks WHERE favourite = 0 LIMIT 15")
    fun getUnfollowed() : Flow<List<Track>>

    @Query("SELECT * FROM tracks WHERE favourite = 1")
    fun getFollowed() : Flow<List<Track>>

    @Query("UPDATE tracks SET favourite = NOT favourite WHERE id = :id")
    suspend fun changeFavourite(id : Int) : Int

    @Query("SELECT * FROM tracks WHERE id= :id")
    fun getTrackWithEvents(id : Int) : Flow<Track>

}