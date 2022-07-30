package com.example.motorsportspotter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert
    fun insert(event: Event)

    @Query("SELECT * FROM events ORDER BY start_date")
    fun getAll() : Flow<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE favourites = 1 ORDER BY start_date")
    fun getFavourites() : Flow<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE uid = :id LIMIT 1")
    fun getById(id : Int) : LiveData<EventWithTrackAndChamp>

    @Query("SELECT * FROM events WHERE (championship_id = :champId OR track_id = :trackId) AND NOT uid = :eventId AND end_date > DATE('now') ORDER BY start_date LIMIT 10")
    fun getSimilarEvents(champId: Int, trackId: Int, eventId:Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId AND start_date > DATE('now') ORDER BY start_date")
    fun getFutureFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId AND (DATE('now') BETWEEN start_date AND end_date) ORDER BY start_date")
    fun getOngoingFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId AND end_date < DATE('now') ORDER BY start_date")
    fun getPastFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

}