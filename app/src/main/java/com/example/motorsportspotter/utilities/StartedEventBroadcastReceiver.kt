package com.example.motorsportspotter.utilities

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
        val resultIntent = Intent(context, EventActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("event_id", intent.getIntExtra("EventId", 0))
        }

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            editIntentAt(0).putExtra("event_id", intent.getIntExtra("EventId", 0))
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(intent.getIntExtra("EventId", 0), PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val builder = NotificationCompat.Builder(context, "EventUpdate")
            .setSmallIcon(R.drawable.ic_events_24)
            .setContentTitle(intent.getStringExtra("Title"))
            .setContentText("Click here to see the event details")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(resultPendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(intent.getIntExtra("NotifyId", 0), builder.build())
        }
    }
}