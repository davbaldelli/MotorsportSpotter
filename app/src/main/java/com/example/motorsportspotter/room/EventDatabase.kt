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


            trackDao.insert(Track(0,"44.344431108468314, 11.719529003311498","Autodromo Enzo e Dino Ferrari","https://i.imgur.com/gAidPWR.jpg","https://i.imgur.com/Zv8dYhd.png","Autodromo Enzo e Dino Ferrari", true))
            trackDao.insert(Track(1,"45.62220618007739, 9.284707054933946","Autodromo Di Monza","https://i.imgur.com/zDnWVPJ.jpg","https://i.imgur.com/stxBber.png","Autodromo Nazionale Monza", true))
            trackDao.insert(Track(2,"43.96145885255295, 12.685946829946975","Misano World Circuit Marco Simoncelli","https://i.imgur.com/HoxRd1p.jpg","https://i.imgur.com/uW4JlT1.jpg","Misano Motocircuito Marco Simoncelli", true))
            trackDao.insert(Track(3, "50.4370457586275, 5.972064864975292", "Circuit de Spa-Francorchamps", "https://i.imgur.com/5gtwcaZ.jpg","https://i.imgur.com/2NwKlj5.png","Circuit National de Francorchamps", true))
            trackDao.insert(Track(4, "37.23203356453769, -8.62849926137467", "Autódromo Internacional do Algarve","https://i.imgur.com/iugziVR.jpg","https://i.imgur.com/hveeJ7a.png","Autódromo Internacional do Algarve", true))
            trackDao.insert(Track(5, "43.25214377879271, 5.793127546031003", "Circuit Paul Ricard","https://i.imgur.com/Mgqj2jn.jpg","https://i.imgur.com/EJScl5k.png","Circuito Paul Ricard", false))
            trackDao.insert(Track(6,"49.33027602490856, 8.57094121348048","Hockenheimring","https://i.imgur.com/WnsLRrv.jpg","https://i.imgur.com/5BAHvLi.png","Hockenheimring", true))
            trackDao.insert(Track(7,"50.334273100421434, 6.942636887699614", "Nürburgring","https://i.imgur.com/9UmxQTj.jpg", "https://i.imgur.com/TCkxzf1.jpg","Nürburgring", true))
            trackDao.insert(Track(8,"41.56858401566927, 2.25731192672401", "Circuit de Barcelona-Catalunya", "https://i.imgur.com/UusSuZi.jpg","https://i.imgur.com/uQ1gfgw.png","Circuito di Catalogna", true))
            trackDao.insert(Track(9, "52.38783183252967, 4.544259434318021", "Circuit Zandvoort", "https://i.imgur.com/n37Fbny.jpg","https://i.imgur.com/LBgYdgm.png","Circuito di Zandvoort", false))
            trackDao.insert(Track(10,"47.220286638250634, 14.765229313416473", "Red Bull Ring", "https://i.imgur.com/BCH7Gz4.jpg", "https://i.imgur.com/iT1Teog.png","Red Bull Ring", true))
            trackDao.insert(Track(11, "39.4879226810197, -0.6289358180675595", "Circuit Ricardo Tormo", "https://i.imgur.com/h8n5cfN.jpg", "https://i.imgur.com/JsCuqAi.jpg","Circuit Ricardo Tormo", false))
            trackDao.insert(Track(12, "27.451154880579313, -81.35361188899327", "Sebring International Raceway", "https://i.imgur.com/rXyU9E0.jpg", "https://i.imgur.com/8wMNOkD.png","Circuito di Sebring", true))
            trackDao.insert(Track(13, "47.956346471012736, 0.20774175218750832", "Circuit de la Sarthe", "https://i.imgur.com/TX3Q2fD.jpg", "https://i.imgur.com/zs4KUtw.png","Circuit de la Sarthe", false))
            trackDao.insert(Track(14, "35.372882282577514, 138.92879996887177", "Fuji Speedway", "https://i.imgur.com/11ADibg.jpg", "https://i.imgur.com/x0OpZUR.jpg", "Circuito del Fuji", false))
            trackDao.insert(Track(15, "26.03694764332214, 50.51072123981255", "Bahrain International Circuit", "https://i.imgur.com/wToPBVm.jpg", "https://i.imgur.com/39Wh6uz.png","Bahrain International Circuit", false))
            trackDao.insert(Track(16, "51.535143806175334, 13.928420398407987", "EuroSpeedway Lausitz","https://i.imgur.com/ewwT1Z2.jpg", "https://i.imgur.com/xW7s6Na.png", "Lausitzring", false))
            trackDao.insert(Track(17, "49.431810139242735, 11.124788682985908", "Norisring", "https://i.imgur.com/v6VTte7.jpg", "https://i.imgur.com/epMXkRn.png", "Norisring", false))
            trackDao.insert(Track(18, "47.58213444244969, 19.25053549666542","Hungaroring", "https://i.imgur.com/X91V6se.jpg", "https://i.imgur.com/QesKKhW.png" , "Hungaroring", true))
            trackDao.insert(Track(19, "51.35959079765026, 0.257210560922282", "Brands Hatch", "https://i.imgur.com/yVq2DAX.jpg", "https://i.imgur.com/enrjZkc.png", "Circuito di Brands Hatch", true))
            trackDao.insert(Track(20, "46.86204060787945, 3.163283779952909", "Circuit de Nevers Magny-Cours", "https://i.imgur.com/DQCzxQW.jpg", "https://i.imgur.com/Ke8vdmO.jpg", "Circuito di Nevers Magny-Cours", false))
            trackDao.insert(Track(21, "1.2915778918991436, 103.86390316123044", "Singapore Street Circuit", "https://i.imgur.com/0FjXH1U.jpg", "https://i.imgur.com/tvcWrA1.png", "Singapore Street Circuit", false))
            trackDao.insert(Track(22, "34.845739369622024, 136.53895191102146", "Suzuka Circuit", "https://i.imgur.com/PGvPCDs.jpg", "https://i.imgur.com/HD0FGZw.png", "Circuito di Suzuka", true))
            trackDao.insert(Track(23, "30.134789555043266, -97.63588328875515", "Circuit Of The Americas" , "https://i.imgur.com/6etZB5Q.jpg", "https://i.imgur.com/CZZ0LIq.jpg", "Circuito delle Americhe", false))
            championshipDao.insert(Championship(0,"Formula 1",2022, "F1","https://i.imgur.com/Z00oqIB.jpg", "https://i.imgur.com/08VyCYc.png", true))
            championshipDao.insert(Championship(1,"GT World Challenge Europe",2022, "GTWC Europe","https://i.imgur.com/YPN6RHj.jpg", "https://i.imgur.com/E2KSk8R.png", true))
            championshipDao.insert(Championship(2,"World Endurance Championship", 2022, "WEC","https://i.imgur.com/1hPGMQb.jpg","https://i.imgur.com/uMkbl5c.png", true))
            championshipDao.insert(Championship(3, "Deutsche Tourenwagen Masters",2022, "DTM", "https://i.imgur.com/KQ2ep4I.jpg","https://i.imgur.com/deJgy9H.png", true))
            championshipDao.insert(Championship(4,"Formula 2", 2022, "F2", "https://i.imgur.com/zGUwAwa.jpg", "https://i.imgur.com/zcmfcya.png", false))
            championshipDao.insert(Championship(5,"Formula 3", 2022, "F3", "https://i.imgur.com/j4RYC28.jpg", "https://i.imgur.com/xIaspSP.png", false))
            championshipDao.insert(Championship(6, "Super GT", 2022, "Super GT", "https://i.imgur.com/CaBfE4C.jpg", "https://i.imgur.com/hGw8xrB.png", false))
            championshipDao.insert(Championship(7, "MotoGP", 2022, "MotoGP", "https://i.imgur.com/3ABc863.jpg", "https://i.imgur.com/V20HyhG.png", false))
            championshipDao.insert(Championship(8,"NASCAR Cup Series", 2022, "NASCAR", "https://i.imgur.com/AMu9wTr.jpg","https://i.imgur.com/c4zWXi6.png", true))
            eventDao.insert(Event(0,"Gran Premio dell'Emilia-Romagna",0,0,"2022-04-22","2022-04-24","https://i.imgur.com/T1KIu0G.jpg?2",true))
            eventDao.insert(Event(1,"Gran Premio D'Italia",1,0,"2022-09-09","2022-09-11","https://i.imgur.com/XtVWUlf.jpg", true))
            eventDao.insert(Event(2,"Round 1 - Imola",0,1,"2022-04-01","2022-04-03","https://i.imgur.com/myshDJU.jpg", true))
            eventDao.insert(Event(3, "Round 6 - Misano", 2, 1,"2022-07-01", "2022-07-03", "https://i.imgur.com/t6HWcyF.jpg", true))
            eventDao.insert(Event(4,"6 Hours Of Monza", 1, 2,"2022-07-08","2022-07-10", "https://i.imgur.com/H9YCaPs.jpg", true))
            eventDao.insert(Event(5,"Belgian Gran Prix",3,0,"2022-08-26","2022-08-28","https://i.imgur.com/kIYSYh0.jpg", true))
            eventDao.insert(Event(6,"Round 7 - TotalEnergies 24 Hours of Spa",3,1,"2022-07-28", "2022-07-31", "https://i.imgur.com/D8t6mcp.jpg", true))
            eventDao.insert(Event(7, "Round 3 - Imola", 0, 3,"2022-06-17", "2022-06-19","https://i.imgur.com/fu9POfW.jpg", true))
            eventDao.insert(Event(8, "Round 6 - Spa-Francorchamps",3 ,3,"2022-09-09", "2022-09-11", "https://i.imgur.com/sXhpNYS.jpg", true))
            eventDao.insert(Event(9, "6 Hours Of Spa-Francorchamps", 3, 2, "2022-05-05","2022-05-07", "https://i.imgur.com/KiZbfho.jpg", true))
            eventDao.insert(Event(10, "Round 1 - Portimão",4, 3, "2022-04-29", "2022-05-01","https://i.imgur.com/SpSE2S2.png", true))
            eventDao.insert(Event(11, "Gran Prix De France", 5, 0 ,"2022-07-22", "2022-07-24", "https://i.imgur.com/x9t7kZf.jpg", true))
            eventDao.insert(Event(12, "Round 4 - Circuit Paul Ricard 1000Km",5,1,"2022-06-03", "2022-06-05", "https://i.imgur.com/nkqfPjg.jpg", true))
            eventDao.insert(Event(13, "Round 8 - Hockenheim",6,1, "2022-09-02", "2022-09-04", "https://i.imgur.com/qYeUmqp.jpg", true))
            eventDao.insert(Event(14, "Round 5 - Nürburgring powered by Mercedes-AMG", 7, 3, "2022-08-26", "2022-08-28", "https://i.imgur.com/kBjtpod.jpg", true))
            eventDao.insert(Event(15, "Round 8 - Hockenheimring", 6, 3, "2022-10-07", "2022-10-09", "https://i.imgur.com/qvbJH4d.jpg", true))
            eventDao.insert(Event(16, "Dutch Gran Prix",9, 0, "2022-09-02", "2022-09-04", "https://i.imgur.com/oTp9iic.jpg", true))
            eventDao.insert(Event(17, "Gran Premio De Espana", 8, 0, "2022-05-20", "2022-05-22", "https://i.imgur.com/hgdZtgJ.jpg", true))
            eventDao.insert(Event(18, "Round 10 - Barcellona", 8, 1, "2022-09-30", "2022-10-02", "https://i.imgur.com/du7CQ1Q.jpg", true))
            eventDao.insert(Event(19, "Round 5 - Zandvoort",9, 1,"2022-06-16", "2022-06-18", "https://i.imgur.com/3K8GLLH.jpg", true))
            eventDao.insert(Event(20, "Grossier Preis Von Osterreich", 10, 0, "2022-07-08", "2022-07-10", "https://i.imgur.com/TNI2q70.jpg", true))
            eventDao.insert(Event(21, "Round 7 - Red Bull Ring powered by REMUS", 10, 3, "2022-09-23", "2022-09-25", "https://i.imgur.com/DDzocdz.jpg", true))
            eventDao.insert(Event(22, "Round 9 - Valencia", 11, 1, "2022-09-16", "2022-09-18", "https://i.imgur.com/jOTz1ea.jpg", true))
            eventDao.insert(Event(23, "24 Hours Of Le Mans", 13, 2, "2022-06-11", "2022-06-12", "https://i.imgur.com/JPkwW46.jpg", true))
            eventDao.insert(Event(24, "1000 Miles Of Sebring", 12, 2, "2022-03-16", "2022-03-18", "https://i.imgur.com/irRe3DL.jpg", true))
            eventDao.insert(Event(25, "6 Hours Of Fuji",14, 2,"2022-09-09" ,"2022-09-11","https://i.imgur.com/ETowGwu.jpg", true))
            eventDao.insert(Event(26, "8 Hours of Bahrain", 15, 2, "2022-11-10", "2022-11-12", "https://i.imgur.com/bHoMwfG.jpg", true))
            eventDao.insert(Event(27, "Bahrain Grand Prix", 15, 0, "2022-03-18", "2022-03-20", "https://i.imgur.com/zZ1ger9.jpg", true))
            eventDao.insert(Event(28,"Round 2 - Lausitzring Turn 1 powered by Autohero", 16, 3, "2022-05-20", "2022-05-22", "https://i.imgur.com/Z3OPGpX.jpg", true))
            eventDao.insert(Event(29, "Round 4 - Norisring powered by BMW M", 17, 3, "2022-07-01", "2022-07-03", "https://i.imgur.com/VwQf8ww.jpg", true))
            eventDao.insert(Event(30, "Hungarian Gran Prix", 18, 0, "2022-07-29", "2022-07-31", "https://i.imgur.com/q6nXsRH.jpg", true))
            eventDao.insert(Event(31, "Round 2 - Brands Hatch", 19, 1, "2022-04-30", "2022-05-01", "https://i.imgur.com/Ra0J9AX.jpg", true))
            eventDao.insert(Event(32, "Round 3- Magny-Cours", 20, 1, "2022-05-11", "2022-05-15", "https://i.imgur.com/uvRIsXi.jpg", true))
            eventDao.insert(Event(33, "Singapore Grand Prix", 21, 0, "2022-09-30", "2022-10-02", "https://i.imgur.com/Xu5QAUo.jpg", true))
            eventDao.insert(Event(34, "Japanese Grand Prix", 22, 0, "2022-11-07", "2022-11-09", "https://i.imgur.com/gB6YVYs.jpg", false))
            eventDao.insert(Event(35, "United States Grand Prix", 23, 0, "2022-11-21", "2022-11-23", "https://i.imgur.com/QO9fmK0.jpg", false))
            eventDao.insert(Event(36, "Imola", 0, 4, "2022-04-22", "2022-04-24", "https://i.imgur.com/tkKJnIS.jpg", false))
            eventDao.insert(Event(37, "Monza", 1, 4, "2022-09-09", "2022-09-11", "https://i.imgur.com/050ZtIT.jpg", false))
            eventDao.insert(Event(38, "Spa-Francorchamps", 3, 4,"2022-08-26", "2022-08-28", "https://i.imgur.com/VJgk97z.jpg", false))
            eventDao.insert(Event(39, "Gran Premio di San Marino e della Riviera di Rimini", 2, 7, "2022-09-02", "2022-09-04", "https://i.imgur.com/EhOQ9Qs.jpg",true))
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


