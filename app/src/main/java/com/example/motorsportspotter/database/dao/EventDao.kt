package com.example.motorsportspotter.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.motorsportspotter.database.entities.Event
import com.example.motorsportspotter.database.entities.EventWithTrackAndChamp
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Upsert
    suspend fun insert(event: Event)

    @Query("UPDATE events SET favourites = NOT favourites WHERE id = :id")
    suspend fun setFavourite(id : Int) : Int

    @Query("DELETE FROM events WHERE id NOT IN(:ids)")
    suspend fun deleteNotExistingEvents(ids : List<Int>)

    @Transaction
    @Query("SELECT * FROM events WHERE end_date >= DATE('NOW') ORDER BY start_date")
    fun getAll() : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT e.* FROM events e JOIN championships c on c.id = e.championship_id JOIN tracks t on t.id = e.track_id WHERE (e.favourites = 1 OR t.favourite = 1 OR c.favourite = 1 ) AND (DATE('now') BETWEEN start_date AND end_date) ORDER BY start_date")
    suspend fun getFavouritesOngoingSync() : List<EventWithTrackAndChamp>

    @Transaction
    @Query("SELECT * FROM events WHERE (start_date <= DATE('now') AND DATE('NOW', '+7 days') <= end_date) OR (start_date BETWEEN DATE('NOW') AND DATE('NOW', '+7 days')) OR (end_date BETWEEN DATE('NOW') AND DATE('NOW', '+7 days')) ORDER BY start_date")
    fun getIncomingEvents() : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT e.* FROM events e JOIN championships c on c.id = e.championship_id JOIN tracks t on t.id = e.track_id WHERE (e.favourites = 1 OR t.favourite = 1 OR c.favourite = 1 ) AND end_date >= DATE('now') ORDER BY start_date")
    fun getFavourites() : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    fun getById(id : Int) : Flow<EventWithTrackAndChamp>
    @Transaction
    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    fun getByIdSync(id : Int) : EventWithTrackAndChamp
    @Transaction
    @Query("SELECT * FROM events WHERE (championship_id = :champId OR track_id = :trackId) AND NOT id = :eventId AND end_date > DATE('now') ORDER BY start_date LIMIT 10")
    fun getSimilarEvents(champId: Int, trackId: Int, eventId:Int) : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE championship_id = :champId AND start_date > DATE('now') ORDER BY start_date")
    fun getFutureFromChampionship(champId : Int) : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE championship_id = :champId AND (DATE('now') BETWEEN start_date AND end_date) ORDER BY start_date")
    fun getOngoingFromChampionship(champId : Int) : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE championship_id = :champId AND end_date < DATE('now') ORDER BY start_date DESC")
    fun getPastFromChampionship(champId : Int) : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE track_id = :trackId AND start_date > DATE('now') ORDER BY start_date")
    fun getFutureFromTrack(trackId : Int) : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE track_id = :trackId AND (DATE('now') BETWEEN start_date AND end_date) ORDER BY start_date")
    fun getOngoingFromTrack(trackId : Int) : Flow<List<EventWithTrackAndChamp>>

    @Transaction
    @Query("SELECT * FROM events WHERE track_id = :trackId AND end_date < DATE('now') ORDER BY start_date DESC")
    fun getPastFromTrack(trackId : Int) : Flow<List<EventWithTrackAndChamp>>

}