package com.example.motorsportspotter.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.motorsportspotter.room.dao.ChampionshipDao
import com.example.motorsportspotter.room.dao.EventDao
import com.example.motorsportspotter.room.dao.SessionDao
import com.example.motorsportspotter.room.dao.TrackDao
import com.example.motorsportspotter.room.entities.Championship
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.entities.Session
import com.example.motorsportspotter.room.entities.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Event::class, Track::class, Championship::class, Session::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun trackDao(): TrackDao
    abstract fun championshipDao(): ChampionshipDao
    abstract fun sessionDao(): SessionDao

    private class EventDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.eventDao(), database.trackDao(), database.championshipDao(), database.sessionDao())
                }
            }
        }

        suspend fun populateDatabase(eventDao: EventDao, trackDao: TrackDao, championshipDao: ChampionshipDao, sessionDao: SessionDao) {

            // Delete all content here.

            // Add sample elements.


            trackDao.insert(Track(0,"","Autodromo Enzo e Dino Ferrari","https://i.imgur.com/gAidPWR.jpg","https://i.imgur.com/Zv8dYhd.png","Autodromo Enzo e Dino Ferrari", true))
            trackDao.insert(Track(1,"","Autodromo Di Monza","https://i.imgur.com/zDnWVPJ.jpg","https://i.imgur.com/stxBber.png","Autodromo Nazionale Monza", true))
            trackDao.insert(Track(2,"","Misano World Circuit Marco Simoncelli","https://i.imgur.com/HoxRd1p.jpg","https://i.imgur.com/uW4JlT1.jpg","Misano Motocircuito Marco Simoncelli", true))
            trackDao.insert(Track(3, "", "Circuit de Spa-Francorchamps", "https://i.imgur.com/5gtwcaZ.jpg","https://i.imgur.com/2NwKlj5.png","Circuit National de Francorchamps", true))
            trackDao.insert(Track(4, "", "Autódromo Internacional do Algarve","https://i.imgur.com/iugziVR.jpg","https://i.imgur.com/hveeJ7a.png","Autódromo Internacional do Algarve", true))
            trackDao.insert(Track(5, "", "Circuit Paul Ricard","https://i.imgur.com/Mgqj2jn.jpg","https://i.imgur.com/EJScl5k.png","Circuito Paul Ricard", false))
            trackDao.insert(Track(6,"","Hockenheimring","https://i.imgur.com/WnsLRrv.jpg","https://i.imgur.com/5BAHvLi.png","Hockenheimring", true))
            trackDao.insert(Track(7,"", "Nürburgring","https://i.imgur.com/9UmxQTj.jpg", "https://i.imgur.com/TCkxzf1.jpg","Nürburgring", true))
            trackDao.insert(Track(8,"", "Circuit de Barcelona-Catalunya", "https://i.imgur.com/UusSuZi.jpg","https://i.imgur.com/uQ1gfgw.png","Circuito di Catalogna", true))
            trackDao.insert(Track(9, "", "Circuit Zandvoort", "https://i.imgur.com/n37Fbny.jpg","https://i.imgur.com/LBgYdgm.png","Circuito di Zandvoort", false))
            trackDao.insert(Track(10,"", "Red Bull Ring", "https://i.imgur.com/BCH7Gz4.jpg", "https://i.imgur.com/iT1Teog.png","Red Bull Ring", true))
            trackDao.insert(Track(11, "", "Circuit Ricardo Tormo", "https://i.imgur.com/h8n5cfN.jpg", "https://i.imgur.com/JsCuqAi.jpg","Circuit Ricardo Tormo", false))
            trackDao.insert(Track(12, "", "Sebring International Raceway", "https://i.imgur.com/rXyU9E0.jpg", "https://i.imgur.com/8wMNOkD.png","Circuito di Sebring", true))
            trackDao.insert(Track(13, "", "Circuit de la Sarthe", "https://i.imgur.com/TX3Q2fD.jpg", "https://i.imgur.com/zs4KUtw.png","Circuit de la Sarthe", false))
            trackDao.insert(Track(14, "", "Fuji Speedway", "https://i.imgur.com/11ADibg.jpg", "https://i.imgur.com/x0OpZUR.jpg", "Circuito del Fuji", false))
            trackDao.insert(Track(15, "", "Bahrain International Circuit", "https://i.imgur.com/wToPBVm.jpg", "https://i.imgur.com/39Wh6uz.png","Bahrain International Circuit", false))
            trackDao.insert(Track(16, "", "EuroSpeedway Lausitz","https://i.imgur.com/ewwT1Z2.jpg", "https://i.imgur.com/xW7s6Na.png", "Lausitzring", false))
            trackDao.insert(Track(17, "", "Norisring", "https://i.imgur.com/v6VTte7.jpg", "https://i.imgur.com/epMXkRn.png", "Norisring", false))
            trackDao.insert(Track(18, "","Hungaroring", "https://i.imgur.com/X91V6se.jpg", "https://i.imgur.com/QesKKhW.png" , "Hungaroring", true))
            trackDao.insert(Track(19, "", "Brands Hatch", "https://i.imgur.com/yVq2DAX.jpg", "https://i.imgur.com/enrjZkc.png", "Circuito di Brands Hatch", true))
            trackDao.insert(Track(20, "", "Circuit de Nevers Magny-Cours", "https://i.imgur.com/DQCzxQW.jpg", "https://i.imgur.com/Ke8vdmO.jpg", "Circuito di Nevers Magny-Cours", false))
            trackDao.insert(Track(21, "", "Singapore Street Circuit", "https://i.imgur.com/0FjXH1U.jpg", "https://i.imgur.com/tvcWrA1.png", "Singapore Street Circuit", false))
            trackDao.insert(Track(22, "", "Suzuka Circuit", "https://i.imgur.com/PGvPCDs.jpg", "https://i.imgur.com/HD0FGZw.png", "Circuito di Suzuka", true))
            trackDao.insert(Track(23, "", "Circuit Of The Americas" , "https://i.imgur.com/6etZB5Q.jpg", "https://i.imgur.com/CZZ0LIq.jpg", "Circuito delle Americhe", false))
            trackDao.insert(Track(24, "", "Autodromo di Vallelunga", "https://i.imgur.com/3aqM2To.jpg","https://i.imgur.com/1DCVGui.png","Vallelunga", false))
            trackDao.insert(Track(25,"","Autodromo internazionale del Mugello", "https://i.imgur.com/IwQ0IWm.jpg", "https://i.imgur.com/dR6HEOC.png", "Mugello Circuit", false))
            trackDao.insert(Track(26, "", "Silverstone Circuit", "https://i.imgur.com/bMEkUTX.jpg", "https://i.imgur.com/lDKGAX1.png", "Circuito di Silverstone", false))
            trackDao.insert(Track(27, "", "Nürburgring Nordschleife", "https://i.imgur.com/MfcGvUb.jpg", "https://i.imgur.com/TCkxzf1.jpg", "Nürburgring Nordschleife", false))
            trackDao.insert(Track(28, "", "Autodrom Most", "https://i.imgur.com/4DAcTbg.jpg", "https://i.imgur.com/mNKzg1Y.png", "Autodromo di Most", false))
            trackDao.insert(Track(29, "", "Motorland Aragòn", "https://i.imgur.com/yuGmfW1.jpg", "https://i.imgur.com/rxIAZGz.jpg", "Ciudad del Motor de Aragón", false))
            trackDao.insert(Track(30,"","Anneau du Rhin", "https://i.imgur.com/4LrcxMj.jpg", "https://i.imgur.com/n8FWKjg.jpg", "Anneau du Rhin", false))
            trackDao.insert(Track(31, "", "Circuit Zolder", "https://i.imgur.com/mnNwnDx.jpg", "https://i.imgur.com/5pmBfHb.png", "Circuito di Zolder", false))
            trackDao.insert(Track(32, "", "Twin Ring Motegi", "https://i.imgur.com/LxI9mGb.jpg", "https://i.imgur.com/lrHwN0u.png", "Twin Ring Motegi", false))
            trackDao.insert(Track(33, "", "Chang International Circuit", "https://i.imgur.com/G9uo5gD.jpg","https://i.imgur.com/zPRZMM7.png", "Chang International Circuit", false ))
            trackDao.insert(Track(34, "", "Phillip Island Grand Prix Circuit", "https://i.imgur.com/yfaHT2S.jpg", "https://i.imgur.com/o7FVR0l.png", "Circuito di Phillip Island (Phillip Island Grand Prix Circuit)", false))
            trackDao.insert(Track(35, "", "Sepang International Circuit", "https://i.imgur.com/0sYRm1K.jpg", "https://i.imgur.com/J1Pe5i6.png", "Circuito di Sepang", false))
            trackDao.insert(Track(36, "", "TT Circuit Assen", "https://i.imgur.com/svsT8KT.jpg", "https://i.imgur.com/1zRUeH0.png", "TT Circuit Assen", false))
            trackDao.insert(Track(37, "", "Circuito de Jerez - Angel Nieto", "https://i.imgur.com/U58rqh4.jpg", "https://i.imgur.com/lQfG9bk.jpg", "Circuito di Jerez de la Frontera", false))
            trackDao.insert(Track(38, "","Sachsenring", "https://i.imgur.com/ZGYXigl.jpg", "https://i.imgur.com/boL5mb2.png", "Sachsenring", false))
            trackDao.insert(Track(39, "","Pertamina Mandalika Circuit", "https://i.imgur.com/6rEeMqs.jpg", "https://i.imgur.com/UXAlfTL.png", "Pertamina Mandalika International Street Circuit", false))
            trackDao.insert(Track(40, "", "Autódromo Internacional de Termas de Río Hondo", "https://i.imgur.com/rIyeN4W.jpg", "https://i.imgur.com/fU2Avar.png", "Autodromo di Termas de Río Hondo (Autódromo Internacional de Termas de Río Hondo", false))
            trackDao.insert(Track(41, "","Pau Circuit", "https://i.imgur.com/6i0Bbb6.jpg", "https://i.imgur.com/5UgNYZv.png", "Circuito di Pau", false))
            trackDao.insert(Track(42,"", "Circuito Internacional de Vila Real", "https://i.imgur.com/uwg1mHf.jpg", "https://i.imgur.com/yyCysiA.jpg", "Circuito Internacional di Vila Real", false))
            trackDao.insert(Track(43, "", "Losail International Circuit", "https://i.imgur.com/AJ8zRBa.jpg", "https://i.imgur.com/iL8FRl7.png", "Circuito di Losail", false))
            trackDao.insert(Track(44, "", "Motorsport Arena Oschersleben", "https://i.imgur.com/LGkSmBA.jpg", "https://i.imgur.com/ayUpUcV.png", "Motorsport Arena Oschersleben", false))
            trackDao.insert(Track(45, "", "Automotodrom Grobnik", "https://i.imgur.com/oUezUQx.jpg", "https://i.imgur.com/TvLXp6U.png", "Automotodrom Grobnik", false))
            trackDao.insert(Track(46, "", "Autódromo Hermanos Rodríguez", "https://i.imgur.com/YXvlCBq.jpg", "https://i.imgur.com/5Wg2hcg.png", "Autódromo Hermanos Rodríguez", false))
            trackDao.insert(Track(47, "", "Interlagos Circuit", "https://i.imgur.com/x3PMmHB.jpg", "https://i.imgur.com/62HoCB6.jpg", "Autódromo José Carlos Pace", false))
            trackDao.insert(Track(48, "", "Yas Marina Circuit", "https://i.imgur.com/o1Yqkgb.jpg", "https://i.imgur.com/QvuCB4T.png", "North Oasis - Yas Marina Circuit", false))
            trackDao.insert(Track(49, "", "Autódromo do Estoril", "https://i.imgur.com/IE9YKrE.jpg", "https://i.imgur.com/NLLloT0.png", "Circuito di Estoril", false))
            trackDao.insert(Track(50, "", "Donington Park", "https://i.imgur.com/mcFeBKJ.jpg", "https://i.imgur.com/Qb7CdKF.png", "Circuito di Donington Park", false))
            trackDao.insert(Track(51, "", "Circuito San Juan Villicum", "https://i.imgur.com/q6yeuzm.jpg", "https://i.imgur.com/93HVV3g.png", "Zona de Acampe - Autódromo El Villicum", false))
            championshipDao.insert(Championship(0,"Formula 1",2022, "F1","https://i.imgur.com/Z00oqIB.jpg", "https://i.imgur.com/08VyCYc.png", true, null))
            championshipDao.insert(Championship(1,"GT World Challenge Europe",2022, "GTWC Europe","https://i.imgur.com/YPN6RHj.jpg", "https://i.imgur.com/E2KSk8R.png", true, "https://www.youtube.com/gtworld/videos"))
            championshipDao.insert(Championship(2,"World Endurance Championship", 2022, "WEC","https://i.imgur.com/1hPGMQb.jpg","https://i.imgur.com/uMkbl5c.png", true, "https://www.fiawec.com/en/live-experience/47"))
            championshipDao.insert(Championship(3, "Deutsche Tourenwagen Masters",2022, "DTM", "https://i.imgur.com/KQ2ep4I.jpg","https://i.imgur.com/deJgy9H.png", true, "https://www.youtube.com/c/DTM/videos"))
            championshipDao.insert(Championship(4,"Formula 2", 2022, "F2", "https://i.imgur.com/zGUwAwa.jpg", "https://i.imgur.com/zcmfcya.png", false, null))
            championshipDao.insert(Championship(5,"Formula 3", 2022, "F3", "https://i.imgur.com/j4RYC28.jpg", "https://i.imgur.com/xIaspSP.png", false, null))
            championshipDao.insert(Championship(6, "Ferrari Challenge Trofeo Pirelli", 2022, "Ferrari Challenge EU", "https://i.imgur.com/Ma9263E.jpg", "https://i.imgur.com/eemMTLq.png", false, "https://www.youtube.com/c/Ferrari/videos"))
            championshipDao.insert(Championship(7, "MotoGP", 2022, "MotoGP", "https://i.imgur.com/3ABc863.jpg", "https://i.imgur.com/V20HyhG.png", false, null))
            championshipDao.insert(Championship(8, "World Turing Car Cup", 2022, "WTCR" , "https://i.imgur.com/Gvwgzr3.jpg", "https://i.imgur.com/EBzb5GK.png", false, "https://www.eurosportplayer.com/sport/touring-car"))
            championshipDao.insert(Championship(9, "EuroNASCAR PRO", 2022, "EuroNASCAR PRO", "https://i.imgur.com/glyOXEt.jpg", "https://i.imgur.com/EAlUhbZ.png", false, "https://www.youtube.com/c/EuroNASCAR/videos"))
            championshipDao.insert(Championship(10, "ADAC GT Masters", 2022, "ADAC GT Masters", "https://i.imgur.com/lBapZTn.jpg", "https://i.imgur.com/n4hCKRs.jpg", false, "https://www.youtube.com/c/ADACMotorsports/videos"))
            championshipDao.insert(Championship(11, "Superbike World Championship", 2022, "WorldSBK", "https://i.imgur.com/oDZJstu.jpg", "https://i.imgur.com/44c10pK.png",false, "https://www.eurosport.it/watch/superbike/" ))
            championshipDao.insert(Championship(12, "European Le Mans Series", 2022, "ELMS", "https://i.imgur.com/sIZaBNy.jpg", "https://i.imgur.com/ge6fQdf.png", false, "https://www.youtube.com/c/EuropeanLeMansSeriesOfficial/videos"))
            eventDao.insert(Event(0,"Gran Premio dell'Emilia-Romagna",0,0,"2022-04-22","2022-04-24","https://i.imgur.com/T1KIu0G.jpg?2",true))
            eventDao.insert(Event(1,"Gran Premio D'Italia",1,0,"2022-09-09","2022-09-11","https://i.imgur.com/XtVWUlf.jpg", true))
            eventDao.insert(Event(2,"Round 1 - Imola",0,1,"2022-04-01","2022-04-03","https://i.imgur.com/myshDJU.jpg", true))
            eventDao.insert(Event(3, "Round 6 - Misano", 2, 1,"2022-07-01", "2022-07-03", "https://i.imgur.com/t6HWcyF.jpg", true))
            eventDao.insert(Event(4,"6 Hours Of Monza", 1, 2,"2022-07-08","2022-07-10", "https://i.imgur.com/H9YCaPs.jpg", true))
            eventDao.insert(Event(5,"Belgian Gran Prix",3,0,"2022-08-26","2022-08-28","https://i.imgur.com/kIYSYh0.jpg", true))
            sessionDao.insert(Session(null, "Race", 5, "2022-08-28 19:34", null, 44))
            eventDao.insert(Event(6,"Round 7 - TotalEnergies 24 Hours of Spa",3,1,"2022-07-28", "2022-07-31", "https://i.imgur.com/D8t6mcp.jpg", true))
            eventDao.insert(Event(7, "Round 3 - Imola", 0, 3,"2022-06-17", "2022-06-19","https://i.imgur.com/fu9POfW.jpg", true))
            eventDao.insert(Event(8, "Round 6 - Spa-Francorchamps",3 ,3,"2022-09-09", "2022-09-11", "https://i.imgur.com/sXhpNYS.jpg", true))
            eventDao.insert(Event(9, "6 Hours Of Spa-Francorchamps", 3, 2, "2022-05-05","2022-05-07", "https://i.imgur.com/KiZbfho.jpg", true))
            eventDao.insert(Event(10, "Round 1 - Portimão",4, 3, "2022-04-29", "2022-05-01","https://i.imgur.com/SpSE2S2.png", true))
            eventDao.insert(Event(11, "Gran Prix De France", 5, 0 ,"2022-07-22", "2022-07-24", "https://i.imgur.com/x9t7kZf.jpg", true))
            eventDao.insert(Event(12, "Round 4 - Circuit Paul Ricard 1000Km",5,1,"2022-06-03", "2022-06-05", "https://i.imgur.com/nkqfPjg.jpg", true))
            eventDao.insert(Event(13, "Round 8 - Hockenheim",6,1, "2022-09-02", "2022-09-04", "https://i.imgur.com/qYeUmqp.jpg", true))
            sessionDao.insert(Session(null,"Free Practise", 13, "2022-09-03 09:45", 90, null))
            sessionDao.insert(Session(null,"Pre-Qualifying Session", 13, "2022-09-03 14:05", 90, null))
            sessionDao.insert(Session(null, "Qualifying", 13, "2022-09-04 09:45", 45, null))
            sessionDao.insert(Session(null, "Race", 13, "2022-09-04 15:00", 60 * 3, null))
            eventDao.insert(Event(14, "Round 5 - Nürburgring powered by Mercedes-AMG", 7, 3, "2022-08-26", "2022-08-29", "https://i.imgur.com/kBjtpod.jpg", true))
            sessionDao.insert(Session(null, "Race 2", 14, "2022-08-28 19:33", null, null))
            eventDao.insert(Event(15, "Round 8 - Hockenheimring", 6, 3, "2022-10-07", "2022-10-09", "https://i.imgur.com/qvbJH4d.jpg", true))
            eventDao.insert(Event(16, "Dutch Gran Prix",9, 0, "2022-09-02", "2022-09-04", "https://i.imgur.com/oTp9iic.jpg", true))
            sessionDao.insert(Session(null, "Free Practise 1", 16, "2022-09-02 12:30", 60, null))
            sessionDao.insert(Session(null, "Free Practise 2", 16, "2022-09-02 16:00", 60, null))
            sessionDao.insert(Session(null, "Free Practise 3", 16, "2022-09-03 12:00", 60, null))
            sessionDao.insert(Session(null, "Qualifying", 16, "2022-09-03 15:00", null, null))
            sessionDao.insert(Session(null, "Race", 16, "2022-09-03 15:00", null, null))
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
            eventDao.insert(Event(34, "Japanese Grand Prix", 22, 0, "2022-10-07", "2022-10-09", "https://i.imgur.com/gB6YVYs.jpg", false))
            eventDao.insert(Event(35, "United States Grand Prix", 23, 0, "2022-10-21", "2022-10-23", "https://i.imgur.com/QO9fmK0.jpg", false))
            eventDao.insert(Event(36, "Imola", 0, 4, "2022-04-22", "2022-04-24", "https://i.imgur.com/tkKJnIS.jpg", false))
            eventDao.insert(Event(37, "Monza", 1, 4, "2022-09-09", "2022-09-11", "https://i.imgur.com/050ZtIT.jpg", false))
            eventDao.insert(Event(38, "Spa-Francorchamps", 3, 4,"2022-08-26", "2022-08-28", "https://i.imgur.com/VJgk97z.jpg", false))
            eventDao.insert(Event(39, "Gran Premio di San Marino e della Riviera di Rimini", 2, 7, "2022-09-02", "2022-09-04", "https://i.imgur.com/EhOQ9Qs.jpg",true))
            sessionDao.insert(Session(null, "Free Practise 1", 39, "2022-09-02 09:55", null, null))
            sessionDao.insert(Session(null, "Free Practise 2", 39, "2022-09-02 14:10", null, null))
            sessionDao.insert(Session(null, "Free Practise 3", 39, "2022-09-03 09:55", null, null))
            sessionDao.insert(Session(null, "Qualifying", 39, "2022-09-03 14:10", null, null))
            sessionDao.insert(Session(null, "Race", 39, "2022-09-04 14:00", null, null))
            eventDao.insert(Event(40, "Grande Prémio Tissot de Portugal", 4, 7, "2022-04-22","2022-04-24", "https://i.imgur.com/5Y6tUyC.jpg", false))
            eventDao.insert(Event(41, "Gran Premio d'Italia Oakley", 25, 7, "2022-05-27", "2022-05-29", "https://i.imgur.com/Y8xKe1B.jpg", false))
            eventDao.insert(Event(42, "Monster Energy British Grand Prix", 26, 7, "2022-08-05", "2022-08-07", "https://i.imgur.com/dfBAQ3P.jpg", false))
            eventDao.insert(Event(43, "CryptoDATA Motorrad Grand Prix von Österreich", 10, 7, "2022-08-19", "2022-08-21", "https://i.imgur.com/pc3AT9v.jpg", false))
            eventDao.insert(Event(44, "Gran Premio Motul de la Comunitat Valenciana", 11, 7, "2022-11-04", "2022-11-06", "https://i.imgur.com/EvLxJac.jpg", false))
            eventDao.insert(Event(45, "NASCAR GP Spain", 11, 9, "2022-05-14", "2022-05-15", "https://i.imgur.com/pNbHDXz.jpg", false))
            eventDao.insert(Event(46, "NASCAR GP UK", 19, 9, "2022-06-11", "2022-06-12", "https://i.imgur.com/YuBDxDb.jpg", false))
            eventDao.insert(Event(47, "NASCAR GP Italy", 24, 9, "2022-07-09", "2022-07-10", "https://i.imgur.com/sWiezCw.jpg", false))
            eventDao.insert(Event(48, "Race Of Italy", 24, 8, "2022-07-23", "2022-07-24", "https://i.imgur.com/Wty97Dz.jpg", false))
            eventDao.insert(Event(49, "British Grand Prix", 26, 0, "2022-07-03", "2022-07-05", "https://i.imgur.com/VNKnXjd.jpg" , false))
            eventDao.insert(Event(50, "Gran Premi Monster Energy de Catalunya", 8, 7,"2022-06-03", "2022-06-05", "https://i.imgur.com/4Dov9bs.jpg", false))
            eventDao.insert(Event(51, "Race Of Germany", 27, 8, "2022-05-26", "2022-05-28", "https://i.imgur.com/NexR6as.jpg", false))
            eventDao.insert(Event(52, "Race Of Hungary", 18, 8, "2022-06-11", "2022-06-12", "https://i.imgur.com/kMz798n.jpg", false))
            eventDao.insert(Event(53, "NASCAR GP Czech Republic", 28, 9, "2022-09-02", "2022-09-04", "https://i.imgur.com/b0OFya4.jpg", false))
            sessionDao.insert(Session(null, "Free Practise 1", 53, "2022-09-02 14:10", 30, null))
            sessionDao.insert(Session(null, "Free Practise 2", 53, "2022-09-02 16:50", 30, null))
            sessionDao.insert(Session(null, "Qualifying", 53, "2022-09-03 10:50", 15, null))
            sessionDao.insert(Session(null, "Superpole", 53, "2022-09-03 11:10", 5, null))
            sessionDao.insert(Session(null, "Race 1", 53, "2022-09-03 13:50", null, null))
            sessionDao.insert(Session(null, "Race 2", 53, "2022-09-04 15:00", null, null))
            eventDao.insert(Event(54, "Gran Premio Animoca Brands de Aragón", 29, 7, "2022-09-16", "2022-09-18", "https://i.imgur.com/ujGyw4v.jpg", false))
            eventDao.insert(Event(56, "Race Of Spain", 29, 8, "2022-06-25", "2022-06-26", "https://i.imgur.com/nGcy1wA.jpg", false))
            eventDao.insert(Event(57, "Red Bull Grand Prix of The Americas", 23, 7, "2022-04-08", "2022-04-10", "https://i.imgur.com/suYb1yM.jpg", false))
            eventDao.insert(Event(58, "Shark Grand Prix de France", 13, 7, "2022-05-13","2022-05-15", "https://i.imgur.com/iFeSDK1.jpg", false))
            eventDao.insert(Event(59, "Race of Alsace GrandEst", 30, 8, "2022-08-05", "2022-08-07", "https://i.imgur.com/Sua1PAt.jpg", false))
            eventDao.insert(Event(60, "NASCAR GP Belgium", 31, 9, "2022-10-08", "2022-10-09", "https://i.imgur.com/ZDiJkK6.jpg", false))
            eventDao.insert(Event(61, "Motul Grand Prix of Japan", 32, 7,"2022-09-23", "2022-08-25", "https://i.imgur.com/XtltJJX.jpg", false))
            eventDao.insert(Event(62, "Thailand Grand Prix ", 33, 7, "2022-09-30", "2022-10-02", "https://i.imgur.com/9svI6ob.jpg", false))
            eventDao.insert(Event(63, "Animoca Brands Australian Motorcycle Grand Prix", 34, 7, "2022-10-14", "2022-10-16", "https://i.imgur.com/cbOsemk.jpg", false))
            eventDao.insert(Event(64, "PETRONAS Grand Prix of Malaysia", 35, 7, "2022-10-21", "2022-10-23", "https://i.imgur.com/ATm92jo.jpg", false))
            eventDao.insert(Event(65, "Gran Premio Red Bull de España", 37, 7, "2022-04-29", "2022-05-01", "https://i.imgur.com/xfZPSzI.jpg", false))
            eventDao.insert(Event(66, "Liqui Moly Motorrad Grand Prix Deutschland", 38, 7, "2022-06-17", "2022-06-19", "https://i.imgur.com/LWdbEsu.jpg", false))
            eventDao.insert(Event(67, "Pertamina Grand Prix of Indonesia", 39, 7, "2022-03-18", "2022-03-20", "https://i.imgur.com/izwgUGN.jpg", false))
            eventDao.insert(Event(68, "Gran Premio Michelin® de la República Argentina", 40, 7, "2022-04-01", "2022-04-03", "https://i.imgur.com/5jbIbbR.jpg", false))
            eventDao.insert(Event(69, "Grand Prix of Qatar", 43, 7, "2022-03-04", "2022-03-06", "https://i.imgur.com/O9VSntb.jpg", false))
            eventDao.insert(Event(70, "Race of France", 41, 8, "2022-05-07", "2022-05-08", "https://i.imgur.com/KnOPU3M.jpg", false))
            eventDao.insert(Event(71, "Race of Portugal", 42, 8, "2022-07-02", "2022-07-03", "https://i.imgur.com/LJlA3aA.jpg", false))
            eventDao.insert(Event(72, "NASCAR GP Croatia", 45, 9, "2022-11-29", "2022-11-30", "https://i.imgur.com/03CsZHy.jpg", false))
            eventDao.insert(Event(73, "Motorsport Arena Oschersleben", 44, 10, "2022-04-22", "2022-04-24","https://i.imgur.com/QRZlEFa.jpg", false))
            eventDao.insert(Event(74, "Red Bull Ring", 10, 10, "2022-05-20", "2022-05-22", "https://i.imgur.com/i3EHrz5.jpg", false))
            eventDao.insert(Event(75, "CM.com Circuit Zandvoort", 9, 10,"2022-06-24", "2022-06-26", "https://i.imgur.com/dqy6B7y.jpg", false))
            eventDao.insert(Event(76, "Nürburgring", 7, 10, "2022-08-05", "2022-08-07", "https://i.imgur.com/fVXzPaE.jpg", false))
            eventDao.insert(Event(77, "Family & Friends Festival DEKRA Lausitzring", 16, 10, "2022-08-19", "2022-08-21", "https://i.imgur.com/aVG2mgY.jpg", false))
            eventDao.insert(Event(78, "Sachsenring", 38, 10 ,"2022-09-23", "2022-09-25", "https://i.imgur.com/eLS1X5I.jpg", false))
            eventDao.insert(Event(79, "Hockenheimring Baden-Württemberg", 6, 10, "2022-10-21", "2022-10-23", "https://i.imgur.com/WCf9VYh.jpg", false))
            eventDao.insert(Event(80, "Gran Premio De La Ciudad De México", 46, 0, "2022-10-28", "2022-10-20", "https://i.imgur.com/vm3Vpgn.jpg", false))
            eventDao.insert(Event(81, "Grande Prêmio De São Paulo 2022", 47, 0, "2022-11-11", "2022-11-13", "https://i.imgur.com/Dsx4Vwn.jpg", false))
            eventDao.insert(Event(82, "Abu Dhabi Grand Prix 2022", 48, 0, "2022-11-18", "2022-11-20", "https://i.imgur.com/03m2Y4O.jpg", false))
            eventDao.insert(Event(83, "Pirelli Aragon Round", 29, 11, "2022-04-08", "2022-04-10", "https://i.imgur.com/VRM21LS.jpg", false))
            eventDao.insert(Event(84, "Motul Dutch Round", 36, 11, "2022-04-22", "2022-04-24", "https://i.imgur.com/EpCWbvv.jpg", false))
            eventDao.insert(Event(85, "Estoril Round", 49, 11, "2022-05-20", "2022-05-22", "https://i.imgur.com/NhG2XQv.jpg", false))
            eventDao.insert(Event(86, "Pirelli Emilia-Romagna Round", 2, 11, "2022-06-10", "2022-06-12", "https://i.imgur.com/5R5DGup.jpg", false))
            eventDao.insert(Event(87, "Prosecco DOC UK Round", 50, 11, "2022-07-15", "2022-07-17", "https://i.imgur.com/NNy9Df5.jpg", false))
            eventDao.insert(Event(88, "Pirelli French Round", 20, 11, "2022-09-09", "2022-09-11", "https://i.imgur.com/DIUTPVR.jpg", false))
            eventDao.insert(Event(89, "Prosecco DOC Czech Round", 28, 11, "2022-07-29", "2022-07-31", "https://i.imgur.com/U5DhKCQ.jpg", false))
            eventDao.insert(Event(90, "Catalunya Round", 8, 11, "2022-09-23", "2022-09-25", "https://i.imgur.com/nh0TU1Q.jpg", false))
            eventDao.insert(Event(91, "Pirelli Portuguese Round", 4, 11, "2022-10-07", "2022-10-09", "https://i.imgur.com/gacbbph.jpg", false))
            eventDao.insert(Event(102, "Pirelli Indonesian Round", 39, 11, "2022-11-11", "2022-11-13", "https://i.imgur.com/1Yfpr3t.jpg", false))
            eventDao.insert(Event(92,"Australian Round", 34, 11, "2022-11-18", "2022-11-20", "https://i.imgur.com/Af29Cp8.jpg", false))
            eventDao.insert(Event(93, "Motul Argentinean Round", 51 , 11, "2022-10-21", "2022-10-23", "https://i.imgur.com/eePNoNY.jpg", false))
            eventDao.insert(Event(94, "Portimao", 4, 6, "2022-04-01", "2022-04-03", "https://i.imgur.com/42mXQGk.jpg", false))
            eventDao.insert(Event(95, "Paul Ricard", 5, 6, "2022-05-13", "2022-05-15", "https://i.imgur.com/shoFxV0.jpg", false))
            eventDao.insert(Event(96, "Budapest", 18, 6, "2022-06-17", "2022-06-19", "https://i.imgur.com/1qBjfbS.jpg", false))
            eventDao.insert(Event(97, "Hockenheim", 6, 6, "2022-07-22", "2022-07-24", "https://i.imgur.com/0F3Ml0a.jpg", false))
            eventDao.insert(Event(98, "Silverstone", 26, 6, "2022-09-16", "2022-09-18", "https://i.imgur.com/Bkp9f1t.jpg", false))
            eventDao.insert(Event(99, "Mugello", 25, 6, "2022-10-07", "2022-10-09", "https://i.imgur.com/eq4Rv9u.jpg", false))
            eventDao.insert(Event(100, "Imola", 0, 6, "2022-10-26", "2022-10-30", "https://i.imgur.com/BmUXS2f.jpg", false))
            eventDao.insert(Event(101, "4 Hours Of Le Castellet", 5, 12, "2022-04-15", "2022-04-17", "https://i.imgur.com/JnYeu50.jpg", false))
            eventDao.insert(Event(103, "4 Hours Of Imola", 0, 12, "2022-05-13", "2022-05-15", "https://i.imgur.com/4Z3Hjvu.jpg", false))
            eventDao.insert(Event(104, "4 Hours Of Monza", 1, 12, "2022-07-01", "2022-07-03", "https://i.imgur.com/7FcCNmb.jpg", false))
            eventDao.insert(Event(105, "4 Hours Of Barcellona", 8, 12, "2022-08-26", "2022-08-28", "https://i.imgur.com/qVDQwog.jpg", false))
            eventDao.insert(Event(106, "4 Hours Of Spa-Francorchamps", 3, 12, "2022-09-23", "2022-09-25", "https://i.imgur.com/I5OK2IV.jpg", false))
            eventDao.insert(Event(107, "4 Hours of Portimão", 4, 12, "2022-10-14", "2022-10-16", "https://i.imgur.com/0DMe5pT.jpg", false))
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


