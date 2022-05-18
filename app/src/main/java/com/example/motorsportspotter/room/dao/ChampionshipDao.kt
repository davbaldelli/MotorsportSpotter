package com.example.motorsportspotter.room.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Championship

interface ChampionshipDao {
    @Insert
    fun addChampionship(championship: Championship)

    @Query("SELECT * FROM championships")
    fun getAll() : List<Championship>

    @Query("SELECT * FROM championships WHERE id = :id")
    fun getChampionshipWithEvents(id : Int) : Championship
}