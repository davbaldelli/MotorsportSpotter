package com.example.motorsportspotter.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.motorsportspotter.database.entities.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(track : Track)

    @Query("SELECT * FROM tracks")
    fun getAll() : Flow<List<Track>>

    @Query("SELECT * FROM tracks WHERE favourite = 0 LIMIT 30")
    fun getUnfollowed() : Flow<List<Track>>

    @Query("SELECT * FROM tracks WHERE favourite = 1")
    fun getFollowed() : Flow<List<Track>>

    @Query("UPDATE tracks SET favourite = NOT favourite WHERE id = :id")
    suspend fun changeFavourite(id : Int) : Int

    @Query("SELECT * FROM tracks WHERE id= :id")
    fun getTrackWithEvents(id : Int) : Flow<Track>

}