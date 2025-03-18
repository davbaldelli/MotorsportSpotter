package com.example.motorsportspotter.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["name", "year"], unique = true)], tableName = "championships")
data class Championship(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "year") val year: Int,

    @ColumnInfo(name = "description") val description : String,

    @ColumnInfo(name = "pretty_name")
    val prettyName: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "logo")
    val logo: String,

    @ColumnInfo(name = "favourite", defaultValue = "0")
    val favourite: Boolean,

    @ColumnInfo(name = "live_stream")
    val liveStreamLink: String?,
)

data class RemoteChampionship(
    val id: Int,

    val name: String,

    val year: Int,

    val description : String,

    @ColumnInfo(name = "pretty_name")
    @SerializedName("prettyName")
    val prettyName: String,

    val image: String,

    val logo: String,

    @ColumnInfo(name = "live_stream")
    @SerializedName("liveStream")
    val liveStreamLink: String?,
)
