package com.example.motorsportspotter.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.dao.EventDao
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.Track

@Database(entities = [Event::class, Track::class, Championship::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun trackDao(): TrackDao
    abstract fun championshipDao(): ChampionshipDao

    companion object{
        private var INSTANCE :EventDatabase? = null

        fun getDatabase(context: Context) : EventDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }

    }

}