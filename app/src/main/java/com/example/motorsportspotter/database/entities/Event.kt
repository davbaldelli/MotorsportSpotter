package com.example.motorsportspotter.database.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [Index(value = ["name", "championship_id"], unique = true)], tableName = "events",
    foreignKeys = [ForeignKey(
        entity = Championship::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("championship_id"),
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Track::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("track_id"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Event(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "track_id", index = true)
    @SerializedName("trackId")
    val trackId: Int,

    @ColumnInfo(name = "championship_id", index = true)
    @SerializedName("championshipId")
    val champId: Int,

    @ColumnInfo(name = "start_date")
    @SerializedName("startDate")
    val startDate: String,

    @ColumnInfo(name = "end_date")
    @SerializedName("endDate")
    val endDate: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "favourites", defaultValue = "0")
    val favourites: Boolean,
)

data class RemoteEvent(
    val id: Int,

    val name: String,

    val description: String,

    @ColumnInfo(name = "track_id", index = true)
    @SerializedName("trackId")
    val trackId: Int,

    @ColumnInfo(name = "championship_id", index = true)
    @SerializedName("championshipId")
    val champId: Int,

    @ColumnInfo(name = "start_date")
    @SerializedName("startDate")
    val startDate: String,

    @ColumnInfo(name = "end_date")
    @SerializedName("endDate")
    val endDate: String,

    val image: String,

)


data class EventWithTrackAndChamp(
    @Embedded val event: Event,
    @Relation(parentColumn = "track_id", entityColumn = "id")
    val track: Track,
    @Relation(parentColumn = "championship_id", entityColumn = "id")
    val championship: Championship,
    @Relation(parentColumn = "id", entityColumn = "event_id")
    val sessions: List<Session>,
)

