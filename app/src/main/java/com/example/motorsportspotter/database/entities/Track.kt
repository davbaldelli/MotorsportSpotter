package com.example.motorsportspotter.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["name"], unique = true)], tableName = "tracks")
data class Track(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "common_name") val commonName: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "image") @SerializedName("image") val image: String,

    @ColumnInfo(name = "logo") @SerializedName("logo") val logo: String,

    @SerializedName("locationName") @ColumnInfo(name = "location_name") val locationName: String,

    @ColumnInfo(name = "favourite") val favourite: Boolean,

    @ColumnInfo(name = "nation_code")

    @SerializedName("nationCode") val nationCode: String,
)
