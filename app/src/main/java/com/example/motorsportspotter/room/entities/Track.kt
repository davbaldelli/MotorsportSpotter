package com.example.motorsportspotter.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)], tableName = "tracks")
data class Track(
        @ColumnInfo(name = "id")@PrimaryKey val uid : Int,
        @ColumnInfo(name = "coordinates") val coordinates: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "image") val image : String,
        @ColumnInfo(name = "logo") val logo : String,
        @ColumnInfo(name = "location_name") val locationName : String,
        @ColumnInfo(name = "favourite") val favourite : Boolean
)
