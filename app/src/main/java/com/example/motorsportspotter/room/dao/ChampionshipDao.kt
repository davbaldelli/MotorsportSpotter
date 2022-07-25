package com.example.motorsportspotter.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.entities.ChampionshipWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionshipDao {
    @Insert
    fun insert(championship: Championship)

    @Query("SELECT * FROM championships")
    fun getAll() : Flow<List<ChampionshipWithEvents>>

    @Query("SELECT * FROM championships WHERE id = :id")
    fun getChampionshipWithEvents(id : Int) : Championship

}