package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.Track
import kotlinx.coroutines.flow.Flow

class TracksRepository(private val dao : TrackDao) {

    val allTracks : Flow<List<Track>> = dao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(track: Track) {
        dao.insert(track)
    }
}