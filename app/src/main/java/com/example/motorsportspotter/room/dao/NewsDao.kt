package com.example.motorsportspotter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.motorsportspotter.room.entities.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: News)

    @Query("SELECT * FROM news ORDER BY date DESC")
    fun getAll() : Flow<List<News>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getById(id : Int) : Flow<News>
}