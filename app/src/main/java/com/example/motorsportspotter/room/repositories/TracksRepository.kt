package com.example.motorsportspotter.room.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TracksRepository(private val dao : TrackDao) {

    val allTracks = dao.getAll()
    val unfollowedTracks = dao.getUnfollowed()
    val followedTracks = dao.getFollowed()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(track: Track) {
        dao.insert(track)
    }

    fun getTrackById(id : Int) = dao.getTrackWithEvents(id)

    suspend fun changeFollowed(id : Int) : Int {
        return withContext(Dispatchers.IO) {
            dao.changeFavourite(id)
        }
    }

}