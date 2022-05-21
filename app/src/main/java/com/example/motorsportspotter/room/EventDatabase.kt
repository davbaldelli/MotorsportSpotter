package com.example.motorsportspotter.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.dao.EventDao
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Event::class, Track::class, Championship::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun trackDao(): TrackDao
    abstract fun championshipDao(): ChampionshipDao

    private class EventDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.eventDao(), database.trackDao(), database.championshipDao())
                }
            }
        }

        suspend fun populateDatabase(eventDao: EventDao, trackDao: TrackDao, championshipDao: ChampionshipDao) {

            // Delete all content here.
            eventDao.deleteAll()
            trackDao.deleteAll()
            championshipDao.deleteAll()

            // Add sample elements.
            trackDao.insert(Track(0,"44.34405459714158, 11.71431871153961","Autodromo Enzo e Dino Ferrari"))
            trackDao.insert(Track(1,"44.34405459714158, 11.71431871153961","Autodromo Di Monza"))
            championshipDao.insert(Championship(0,"Formula 1",2022))
            championshipDao.insert(Championship(1,"GT WORLD CHALLENGE",2022))
            eventDao.insert(Event(0,"Gran Premio dell'Emilia-Romagna",0,0,"25/5/2022",""))
            eventDao.insert(Event(2,"Gran Premio D'Italia",1,0,"25/5/2022",""))
            eventDao.insert(Event(1,"3 Hours Of Imola",0,1,"25/5/2022",""))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EventDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "word_database"
                )
                    .addCallback(EventDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }



}


