package com.example.motorsportspotter.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.room.EventDatabase
import com.example.motorsportspotter.room.repositories.EventRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartedEventBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val repo = EventRepository(EventDatabase.getDatabase(context, CoroutineScope(CoroutineName("eventNotification"))).eventDao())
        runBlocking{
            launch {
                val events = repo.getFavSync()
                events.forEach { event ->
                    val builder = NotificationCompat.Builder(context, "0")
                        .setSmallIcon(R.drawable.ic_favorite_24)
                        .setContentTitle(event.event.name)
                        .setContentText(event.event.startDate)
                        .setStyle(NotificationCompat.BigTextStyle()
                            .bigText(event.event.startDate))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                    with(NotificationManagerCompat.from(context)) {
                        // notificationId is a unique int for each notification that you must define
                        notify(event.event.uid, builder.build())
                    }
                }
            }
        }
    }
}