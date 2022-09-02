package com.example.motorsportspotter.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["name"], unique = true)], tableName = "tracks")
data class Track(
        @PrimaryKey
        val id : Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "image")
        @SerializedName("background_url")
        val image : String,
        @ColumnInfo(name = "logo")
        @SerializedName("logo_url")
        val logo : String,
        @SerializedName("location")
        @ColumnInfo(name = "location_name")
        val locationName : String,
        @ColumnInfo(name = "favourite") val favourite : Boolean
)
