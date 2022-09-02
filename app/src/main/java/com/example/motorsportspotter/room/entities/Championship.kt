package com.example.motorsportspotter.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["name","year"], unique = true)], tableName = "championships")
data class Championship(
        @PrimaryKey
        val id : Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "year") val year: Int,

        @ColumnInfo(name = "pretty_name")
        @SerializedName("pretty_name")
        val prettyName : String,

        @ColumnInfo(name = "image")
        @SerializedName("background_url")
        val image : String,

        @ColumnInfo(name = "logo")
        @SerializedName("logo_url")
        val logo : String,

        @ColumnInfo(name = "favourite") val favourite : Boolean,

        @ColumnInfo(name = "live_stream")
        @SerializedName("live_stream_link")
        val liveStreamLink : String?,
)
