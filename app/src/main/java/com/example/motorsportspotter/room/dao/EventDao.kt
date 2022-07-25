package com.example.motorsportspotter.room.dao

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM events ORDER BY date")
    fun getAll() : Flow<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId ORDER BY date")
    fun getAllFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

}