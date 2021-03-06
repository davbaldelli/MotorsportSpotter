package com.example.motorsportspotter.room.entities

import androidx.room.*
import java.util.*

@Entity(indices = [Index(value = ["name","championship_id"], unique = true)], tableName = "events")
data class Event(
        @PrimaryKey val uid : Int,
        @ColumnInfo(name = "name") val name : String,
        @ColumnInfo(name = "track_id") val trackId : Int,
        @ColumnInfo(name = "championship_id") val champId : Int,
        @ColumnInfo(name = "date") val date : String,
        @ColumnInfo(name = "image") val image : String,
)

data class EventWithTrackAndChamp(
        @Embedded val event : Event,
        @Relation( parentColumn = "track_id", entityColumn = "id")
        val track: Track,
        @Relation( parentColumn = "championship_id", entityColumn = "id")
        val championship: Championship
)