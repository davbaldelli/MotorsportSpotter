package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.Track

class TracksRepository(private val dao : TrackDao) {

    val allTracks = dao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(track: Track) {
        dao.insert(track)
    }

    fun getTrackById(id : Int) = dao.getTrackWithEvents(id)
}