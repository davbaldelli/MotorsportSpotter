package com.example.motorsportspotter.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.dao.EventDao
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Event::class, Track::class, Championship::class], views  = [EventWithTrack::class, EventWithChampionship::class], version = 1, exportSchema = false)
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

            // Add sample elements.
            trackDao.insert(Track(0,"44.344431108468314, 11.719529003311498","Autodromo Enzo e Dino Ferrari","https://i.imgur.com/gAidPWR.jpg","https://i.imgur.com/Zv8dYhd.png"))
            trackDao.insert(Track(1,"45.62220618007739, 9.284707054933946","Autodromo Di Monza","https://i.imgur.com/zDnWVPJ.jpg","https://i.imgur.com/stxBber.png"))
            trackDao.insert(Track(2,"43.96145885255295, 12.685946829946975","Misano World Circuit Marco Simoncelli","https://i.imgur.com/HoxRd1p.jpg","https://i.imgur.com/uW4JlT1.jpg"))
            trackDao.insert(Track(3, "50.4370457586275, 5.972064864975292", "Circuit de Spa-Francorchamps", "https://i.imgur.com/5gtwcaZ.jpg","https://i.imgur.com/2NwKlj5.png"))
            trackDao.insert(Track(4, "37.23203356453769, -8.62849926137467", "Autódromo Internacional do Algarve","https://i.imgur.com/iugziVR.jpg","https://i.imgur.com/hveeJ7a.png"))
            trackDao.insert(Track(5, "43.25214377879271, 5.793127546031003", "Circuit Paul Ricard","https://i.imgur.com/Mgqj2jn.jpg","https://i.imgur.com/EJScl5k.png"))
            trackDao.insert(Track(6,"49.33027602490856, 8.57094121348048","Hockenheimring","https://i.imgur.com/WnsLRrv.jpg","https://i.imgur.com/5BAHvLi.png"))
            trackDao.insert(Track(7,"50.334273100421434, 6.942636887699614", "Nürburgring","https://i.imgur.com/9UmxQTj.jpg", "https://i.imgur.com/TCkxzf1.jpg"))
            championshipDao.insert(Championship(0,"Formula 1",2022, "F1","https://i.imgur.com/Z00oqIB.jpg", "https://i.imgur.com/08VyCYc.png"))
            championshipDao.insert(Championship(1,"GT World Challenge Europe",2022, "GTWC Europe","https://i.imgur.com/YPN6RHj.jpg", "https://i.imgur.com/E2KSk8R.png"))
            championshipDao.insert(Championship(2,"World Endurance Championship", 2022, "WEC","https://i.imgur.com/1hPGMQb.jpg","https://i.imgur.com/uMkbl5c.png"))
            championshipDao.insert(Championship(3, "Deutsche Tourenwagen Masters",2022, "DTM", "https://i.imgur.com/KQ2ep4I.jpg","https://i.imgur.com/deJgy9H.png"))
            eventDao.insert(Event(0,"Gran Premio dell'Emilia-Romagna",0,0,"2022-04-22","2022-04-24","https://i.imgur.com/T1KIu0G.jpg?2"))
            eventDao.insert(Event(1,"Gran Premio D'Italia",1,0,"2022-09-09","2022-09-11","https://i.imgur.com/XtVWUlf.jpg"))
            eventDao.insert(Event(2,"Round 1 - Imola",0,1,"2022-04-01","2022-04-03","https://i.imgur.com/myshDJU.jpg"))
            eventDao.insert(Event(3, "Round 6 - Misano", 2, 1,"2022-07-01", "2022-07-03", "https://i.imgur.com/t6HWcyF.jpg"))
            eventDao.insert(Event(4,"6 Hours Of Monza", 1, 2,"2022-07-08","2022-07-10", "https://i.imgur.com/H9YCaPs.jpg"))
            eventDao.insert(Event(5,"Belgian Gran Prix",3,0,"2022-08-26","2022-08-28","https://i.imgur.com/kIYSYh0.jpg"))
            eventDao.insert(Event(6,"Round 7 - TotalEnergies 24 Hours of Spa",3,1,"2022-07-28", "2022-07-30", "https://i.imgur.com/D8t6mcp.jpg"))
            eventDao.insert(Event(7, "Round 3 - Imola", 0, 3,"2022-06-17", "2022-06-19","https://i.imgur.com/fu9POfW.jpg"))
            eventDao.insert(Event(8, "Round 6 - Spa-Francorchamps",3 ,3,"2022-09-09", "2022-09-11", "https://i.imgur.com/sXhpNYS.jpg"))
            eventDao.insert(Event(9, "6 Hours Of Spa-Francorchamps", 3, 2, "2022-05-05","2022-05-07", "https://i.imgur.com/KiZbfho.jpg"))
            eventDao.insert(Event(10, "Round 1 - Portimão",4, 3, "2022-04-29", "2022-05-01","https://i.imgur.com/SpSE2S2.png"))
            eventDao.insert(Event(11, "Gran Prix De France", 5, 0 ,"2022-07-22", "2022-07-24", "https://i.imgur.com/x9t7kZf.jpg"))
            eventDao.insert(Event(12, "Round 4 - Circuit Paul Ricard 1000Km",5,1,"2022-06-03", "2022-06-05", "https://i.imgur.com/nkqfPjg.jpg"))
            eventDao.insert(Event(13, "Round 8 - Hockenheim",6,1, "2022-09-02", "2022-09-04", "https://i.imgur.com/qYeUmqp.jpg"))
            eventDao.insert(Event(14, "Round 5 - Nürburgring powered by Mercedes-AMG", 7, 3, "2022-08-26", "2022-08-28", "https://i.imgur.com/kBjtpod.jpg"))
            eventDao.insert(Event(15, "Round 8 - Hockenheimring", 6, 3, "2022-10-07", "2022-10-09", "https://i.imgur.com/qvbJH4d.jpg"))
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


