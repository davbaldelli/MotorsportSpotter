package com.example.motorsportspotter.database.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(indices =  [Index(value = ["name", "event_id"], unique = true)], tableName = "sessions",
    foreignKeys = [ForeignKey(entity = Event::class, parentColumns = arrayOf("id"), childColumns = arrayOf("event_id"))])
data class Session(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "event_id", index = true)
    @SerializedName("eventId")
    val eventId : Int,
    @ColumnInfo(name = "date") val date : String,
    @ColumnInfo(name = "time") val time : String,
    @ColumnInfo(name = "timezone") val timezone : String,
    @ColumnInfo(name = "duration_min")
    @SerializedName("durationMin")
    val durationMin: Int?,
    @ColumnInfo(name = "duration_laps")
    @SerializedName("durationLaps")
    val durationLaps: Int?,
)