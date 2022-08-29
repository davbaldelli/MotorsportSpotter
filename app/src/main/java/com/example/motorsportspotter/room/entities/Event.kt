package com.example.motorsportspotter.room.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["name","championship_id"], unique = true)], tableName = "events",
        foreignKeys = [ForeignKey(entity = Championship::class, parentColumns = arrayOf("id"), childColumns = arrayOf("championship_id")),
                        ForeignKey(entity = Track::class, parentColumns = arrayOf("id"), childColumns = arrayOf("track_id"))]
)
data class Event(
        @PrimaryKey
        val id : Int,
        @ColumnInfo(name = "name")
        val name : String,
        @ColumnInfo(name = "track_id", index = true)
        @SerializedName("track_id")
        val trackId : Int,
        @ColumnInfo(name = "championship_id", index = true)
        @SerializedName("championship_id")
        val champId : Int,
        @ColumnInfo(name = "start_date")
        @SerializedName("start_date")
        val startDate : String,
        @ColumnInfo(name = "end_date")
        @SerializedName("end_date")
        val endDate : String,
        @ColumnInfo(name = "image")
        val image : String,
        @ColumnInfo(name = "favourites")
        val favourites: Boolean
)


data class EventWithTrackAndChamp(
        @Embedded val event : Event,
        @Relation( parentColumn = "track_id", entityColumn = "id")
        val track: Track,
        @Relation( parentColumn = "championship_id", entityColumn = "id")
        val championship: Championship,
        @Relation(parentColumn = "id", entityColumn = "event_id")
        val sessions : List<Session>
)

@Entity(indices =  [Index(value = ["name", "event_id"], unique = true)], tableName = "sessions",
        foreignKeys = [ForeignKey(entity = Event::class, parentColumns = arrayOf("id"), childColumns = arrayOf("event_id"))])
data class Session(
        @PrimaryKey(autoGenerate = true) val id : Int?,
        @ColumnInfo(name = "name") val name : String,
        @ColumnInfo(name = "event_id", index = true)
        @SerializedName("event_id")
        val eventId : Int,
        @ColumnInfo(name = "datetime") val datetime : String,
        @ColumnInfo(name = "duration_min")
        @SerializedName("duration_min")
        val durationMin: Int?,
        @ColumnInfo(name = "duration_laps")
        @SerializedName("duration_laps")
        val durationLaps: Int?,
)