package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.NewsDao
import com.example.motorsportspotter.room.entities.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val dao : NewsDao) {

    val allNews = dao.getAll()

    @WorkerThread
    suspend fun insert(news: News) {
        withContext(Dispatchers.IO){
            dao.insert(news)
        }
    }
}