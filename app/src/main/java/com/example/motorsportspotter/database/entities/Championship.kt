package com.example.motorsportspotter.database.entities

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
        @SerializedName("prettyName")
        val prettyName : String,

        @ColumnInfo(name = "image")
        @SerializedName("image")
        val image : String,

        @ColumnInfo(name = "logo")
        @SerializedName("logo")
        val logo : String,

        @ColumnInfo(name = "favourite") val favourite : Boolean,

        @ColumnInfo(name = "live_stream")
        @SerializedName("liveStream")
        val liveStreamLink : String?,
)
