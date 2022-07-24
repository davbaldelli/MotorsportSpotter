package com.example.motorsportspotter.room.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*


data class News(
    @ColumnInfo(name = "id")@PrimaryKey val uid : Int,
    @ColumnInfo(name = "tile") val title : String,
    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "content") val content : String,
    @ColumnInfo(name = "date") val date : Date
)
