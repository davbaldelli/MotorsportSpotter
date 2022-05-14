package com.example.motorsportspotter.room.entities

import androidx.room.*

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Track(
        @ColumnInfo(name = "id")@PrimaryKey val uid : Int,
        @ColumnInfo(name = "coordinates") val coordinates: String?,
        @ColumnInfo(name = "name") val name: String?,
)

data class TrackWithEvents(
        @Embedded val track: Track,
        @Relation( parentColumn = "track_id", entityColumn = "track")
        val events : List<Event>
)
