package com.example.motorsportspotter.database

import android.app.Application
import com.example.motorsportspotter.database.EventDatabase
import com.example.motorsportspotter.database.repositories.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EventsApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { EventDatabase.getDatabase(this,applicationScope) }
    val eventRepository by lazy { EventRepository(database.eventDao()) }
    val tracksRepository by lazy { TracksRepository(database.trackDao()) }
    val championshipRepository by lazy { ChampionshipRepository(database.championshipDao()) }
    val sessionRepository by lazy { SessionRepository(database.sessionDao()) }
}