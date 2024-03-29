package com.example.motorsportspotter.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.motorsportspotter.database.EventDatabase
import com.example.motorsportspotter.database.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.database.repositories.EventRepository
import kotlinx.coroutines.*
import java.util.*

class DailyEventNotificationPlanner: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val scope = CoroutineScope(Dispatchers.IO + Job())
        val repo = EventRepository(EventDatabase.getDatabase(context, scope).eventDao())
        scope.launch {
            val events = repo.getFavSync()
            events.forEach { event ->
                val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                DBEntitiesConvertersFactory.SessionConverter.convertAll(event.sessions).forEach { session ->
                    val alarmIntent2 = Intent(context, StartedEventBroadcastReceiver::class.java).let { intent ->
                        intent.putExtra("Title", event.championship.prettyName)
                        intent.putExtra("Description", "${event.event.name} ${session.name} is about to start")
                        intent.putExtra("EventId", event.event.id)
                        intent.putExtra("NotifyId", session.id)
                        PendingIntent.getBroadcast(context, session.id!!, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
                    }

                    val calendar: Calendar = Calendar.getInstance().apply {
                        set(Calendar.YEAR, session.startDateTime().year)
                        set(Calendar.MONTH, session.startDateTime().monthValue - 1)
                        set(Calendar.DAY_OF_MONTH, session.startDateTime().dayOfMonth)
                        set(Calendar.HOUR_OF_DAY, session.startDateTime().hour)
                        set(Calendar.MINUTE, session.startDateTime().minute)
                        set(Calendar.SECOND, 0)
                    }

                    if(System.currentTimeMillis() < (calendar.timeInMillis - (60 * 5 * 1000))){
                        alarmMgr.setExact(
                            AlarmManager.RTC_WAKEUP,
                            calendar.timeInMillis - (60 * 5 * 1000),
                            alarmIntent2
                        )
                    }
                }

            }
        }
    }

}