package com.example.motorsportspotter.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.motorsportspotter.database.entities.Championship
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionshipDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(championship: Championship)

    @Query("SELECT * FROM championships")
    fun getAll() : Flow<List<Championship>>

    @Query("SELECT * FROM championships WHERE favourite = 0")
    fun getUnfollowed() : Flow<List<Championship>>

    @Query("SELECT * FROM championships WHERE favourite = 1")
    fun getFollowed() : Flow<List<Championship>>

    @Query("UPDATE championships SET favourite = NOT favourite WHERE id = :id")
    suspend fun changeFavourite(id : Int) : Int

    @Query("SELECT * FROM championships WHERE id = :id")
    fun getChampionshipWithEvents(id : Int) : Flow<Championship>

}