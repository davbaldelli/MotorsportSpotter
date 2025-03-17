package com.example.motorsportspotter.database.repositories

import androidx.annotation.WorkerThread
import com.example.motorsportspotter.database.dao.TrackDao
import com.example.motorsportspotter.database.entities.Track
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

    suspend fun clearNotExistingTracks(ids : List<Int>) {
        withContext(Dispatchers.IO) {
            dao.clearNotExistingTracks(ids)
        }
    }

}