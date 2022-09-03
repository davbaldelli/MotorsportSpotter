package com.example.motorsportspotter.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "news")
data class News(
    @ColumnInfo(name = "id")@PrimaryKey val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "content") val content : String,
    @ColumnInfo(name = "date") val date : String
)
