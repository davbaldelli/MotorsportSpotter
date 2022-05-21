package com.example.motorsportspotter

import android.app.Application
import com.example.motorsportspotter.room.EventDatabase
import com.example.motorsportspotter.room.repositories.ChampionshipRepository
import com.example.motorsportspotter.room.repositories.EventRepository
import com.example.motorsportspotter.room.repositories.TracksRepository

class EventsApplication : Application() {
    private val database by lazy { EventDatabase.getDatabase(this) }
    val eventRepository by lazy { EventRepository(database.eventDao()) }
    val tracksRepository by lazy { TracksRepository(database.trackDao()) }
    val championshipRepository by lazy { ChampionshipRepository(database.championshipDao()) }
}