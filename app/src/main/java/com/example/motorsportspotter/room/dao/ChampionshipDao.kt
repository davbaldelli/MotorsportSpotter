package com.example.motorsportspotter.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Championship
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionshipDao {
    @Insert
    fun insert(championship: Championship)

    @Query("SELECT * FROM championships")
    fun getAll() : Flow<List<Championship>>

    @Query("SELECT * FROM championships WHERE id = :id")
    fun getChampionshipWithEvents(id : Int) : Championship

    @Query("DELETE FROM championships")
    suspend fun deleteAll()

}