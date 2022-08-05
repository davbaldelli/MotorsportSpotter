package com.example.motorsportspotter.utilities

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.activities.EventActivity
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

                    val resultIntent = Intent(context, EventActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        putExtra("event_id", event.event.uid)
                    }

                    val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
                        // Add the intent, which inflates the back stack
                        addNextIntentWithParentStack(resultIntent)
                        editIntentAt(0).putExtra("event_id", event.event.uid)
                        // Get the PendingIntent containing the entire back stack
                        getPendingIntent(event.event.uid, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                    }

                    val builder = NotificationCompat.Builder(context, "EventUpdate")
                        .setSmallIcon(R.drawable.ic_favorite_24)
                        .setContentTitle("${event.event.name} of ${event.championship.prettyName} has just started!")
                        .setContentText("Click here to see the event details")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true)

                    with(NotificationManagerCompat.from(context)) {
                        // notificationId is a unique int for each notification that you must define
                        notify(event.event.uid, builder.build())
                    }
                }
            }
        }
    }
}