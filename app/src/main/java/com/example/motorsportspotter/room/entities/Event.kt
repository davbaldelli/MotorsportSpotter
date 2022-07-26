package com.example.motorsportspotter.room.entities

import androidx.room.*

@Entity(indices = [Index(value = ["name","championship_id"], unique = true)], tableName = "events")
data class Event(
        @PrimaryKey val uid : Int,
        @ColumnInfo(name = "name") val name : String,
        @ColumnInfo(name = "track_id") val trackId : Int,
        @ColumnInfo(name = "championship_id") val champId : Int,
        @ColumnInfo(name = "start_date") val startDate : String,
        @ColumnInfo(name = "end_date") val endDate : String,
        @ColumnInfo(name = "image") val image : String,
)

@DatabaseView("SELECT events.* , tracks.name as track_name FROM events JOIN tracks ON track_id = tracks.id")
data class EventWithTrack(
        @PrimaryKey val uid : Int,
        @ColumnInfo(name = "name") val name : String,
        @ColumnInfo(name = "track_id") val trackId : Int,
        @ColumnInfo(name = "championship_id") val champId : Int,
        @ColumnInfo(name = "start_date") val startDate : String,
        @ColumnInfo(name = "end_date") val endDate : String,
        @ColumnInfo(name = "image") val image : String,
        @ColumnInfo(name ="track_name")val trackName : String,
)

@DatabaseView("SELECT events.* , championships.name as championship_name FROM events JOIN championships ON championship_id = championships.id")
data class EventWithChampionship(
        @PrimaryKey val uid : Int,
        @ColumnInfo(name = "name") val name : String,
        @ColumnInfo(name = "track_id") val trackId : Int,
        @ColumnInfo(name = "championship_id") val champId : Int,
        @ColumnInfo(name = "start_date") val startDate : String,
        @ColumnInfo(name = "end_date") val endDate : String,
        @ColumnInfo(name = "image") val image : String,
        @ColumnInfo(name ="championship_name")val championshipName : String,
)

data class EventWithTrackAndChamp(
        @Embedded val event : Event,
        @Relation( parentColumn = "track_id", entityColumn = "id")
        val track: Track,
        @Relation( parentColumn = "championship_id", entityColumn = "id")
        val championship: Championship
)