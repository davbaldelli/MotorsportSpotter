package com.example.motorsportspotter.room.entities

import androidx.room.*

@Entity(indices = [Index(value = ["name","year"], unique = true)], tableName = "championships")
data class Championship(
        @ColumnInfo(name = "id")@PrimaryKey val uid : Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "year") val year: Int,
        @ColumnInfo(name = "pretty_name") val prettyName : String,
        @ColumnInfo(name = "image") val image : String,
        @ColumnInfo(name = "logo") val logo : String,
        @ColumnInfo(name = "favourite") val favourite : Boolean
)

class ChampionshipWithEvents(
        @Embedded val championship: Championship,
        @Relation(
                parentColumn = "id",
                entityColumn = "championship_id"
        )
        val events : List<EventWithTrack>,
)
