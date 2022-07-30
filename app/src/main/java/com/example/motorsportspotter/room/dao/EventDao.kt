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

    @Query("UPDATE events SET favourites = NOT favourites WHERE uid = :id")
    fun setCarFavourite(id : Int)

    @Query("SELECT * FROM events ORDER BY start_date")
    fun getAll() : Flow<List<EventWithTrackAndChamp>>

    @Query("SELECT e.* FROM events e JOIN championships c on c.id = e.championship_id JOIN tracks t on t.id = e.track_id WHERE e.favourites = 1 OR t.favourite = 1 OR c.favourite = 1 ORDER BY start_date")
    fun getFavourites() : Flow<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE uid = :id LIMIT 1")
    fun getById(id : Int) : LiveData<EventWithTrackAndChamp>

    @Query("SELECT * FROM events WHERE (championship_id = :champId OR track_id = :trackId) AND NOT uid = :eventId AND end_date > DATE('now') ORDER BY start_date LIMIT 10")
    fun getSimilarEvents(champId: Int, trackId: Int, eventId:Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId AND start_date > DATE('now') ORDER BY start_date")
    fun getFutureFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId AND (DATE('now') BETWEEN start_date AND end_date) ORDER BY start_date")
    fun getOngoingFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE championship_id = :champId AND end_date < DATE('now') ORDER BY start_date DESC")
    fun getPastFromChampionship(champId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE track_id = :trackId AND start_date > DATE('now') ORDER BY start_date")
    fun getFutureFromTrack(trackId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE track_id = :trackId AND (DATE('now') BETWEEN start_date AND end_date) ORDER BY start_date")
    fun getOngoingFromTrack(trackId : Int) : LiveData<List<EventWithTrackAndChamp>>

    @Query("SELECT * FROM events WHERE track_id = :trackId AND end_date < DATE('now') ORDER BY start_date DESC")
    fun getPastFromTrack(trackId : Int) : LiveData<List<EventWithTrackAndChamp>>

}