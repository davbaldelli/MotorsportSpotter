package com.example.motorsportspotter.room.entities

import androidx.room.*

@Entity(indices = [Index(value = ["name"], unique = true)], tableName = "tracks")
data class Track(
        @ColumnInfo(name = "id")@PrimaryKey val uid : Int,
        @ColumnInfo(name = "coordinates") val coordinates: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "image") val image : String,
        @ColumnInfo(name = "logo") val logo : String,
)

data class TrackWithEvents(
        @Embedded val track: Track,
        @Relation( parentColumn = "id", entityColumn = "track_id")
        val events : List<EventWithChampionship>
)
