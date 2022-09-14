package com.example.motorsportspotter.utilities

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.example.motorsportspotter.room.EventDatabase
import com.example.motorsportspotter.room.entities.*
import com.example.motorsportspotter.room.repositories.*
import com.example.motorsportspotter.services.retrofit.RemoteClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SyncService : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null
    private lateinit var eventRepo : EventRepository
    private lateinit var tracksRepo : TracksRepository
    private lateinit var champRepo : ChampionshipRepository
    private lateinit var sessionsRepo : SessionRepository
    private lateinit var newsRepo : NewsRepository

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
            val scope = CoroutineScope(Dispatchers.IO + Job())
            val db = EventDatabase.getDatabase(applicationContext, scope)
            eventRepo = EventRepository(db.eventDao())
            tracksRepo = TracksRepository(db.trackDao())
            champRepo = ChampionshipRepository(db.championshipDao())
            sessionsRepo = SessionRepository(db.sessionDao())
            newsRepo = NewsRepository(db.newsDao())

            val service = RemoteClient.getRemoteService()

            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {
                        try {
                            val champsRes = service.allChampionships()?.execute()
                            champsRes?.body()?.let {
                                it.forEach { champ ->
                                    champRepo.insert(champ)
                                }
                            }

                            val trackRes = service.allTracks()?.execute()
                            trackRes?.body()?.let {
                                it.forEach { track ->
                                    tracksRepo.insert(track)
                                }
                            }

                            val eventRes = service.allEvents()?.execute()

                            eventRes?.body()?.let {
                                it.forEach { event ->
                                    runBlocking {
                                        launch {
                                            eventRepo.insert(event)
                                        }
                                    }
                                }
                            }

                            val sessionsRes = service.allSessions()?.execute()

                            sessionsRes?.body()?.let { sessions ->
                                sessions.forEach { session ->
                                    runBlocking {
                                        launch {
                                            sessionsRepo.insert(session)
                                        }
                                    }
                                }
                            }

                            val newsRes = service.allNews()?.execute()

                            newsRes?.body()?.let {
                                it.forEach { news ->
                                    runBlocking {
                                        launch {
                                            newsRepo.insert(news)
                                        }
                                    }
                                }
                            }

                        } catch (e: IOException) {

                        }
                    }
                }
            }

        }
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }


}