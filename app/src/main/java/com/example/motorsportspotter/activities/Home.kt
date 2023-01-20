package com.example.motorsportspotter.activities

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.home.DiscoverFragment
import com.example.motorsportspotter.fragments.home.FavouritesEventFragment
import com.example.motorsportspotter.fragments.home.HomeFragment
import com.example.motorsportspotter.fragments.home.NewsFragment
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.room.viewmodel.TracksViewModel
import com.example.motorsportspotter.room.viewmodel.TracksViewModelFactory
import com.example.motorsportspotter.utilities.DailyEventNotificationPlanner
import com.example.motorsportspotter.utilities.SyncService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Home : AppCompatActivity() {

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((application as EventsApplication).championshipRepository)
    }

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((application as EventsApplication).tracksRepository)
    }

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setBottomNavBehaviour(this)
        createNotificationChannel()

        val alarmMgr = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(applicationContext, DailyEventNotificationPlanner::class.java).let { intent ->
            PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmMgr.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime(),
            AlarmManager.INTERVAL_HOUR,
            alarmIntent
        )

        Intent(this, SyncService::class.java).also { intent ->
            startService(intent)
        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Events Updates"
            val descriptionText = "Notifications concerning the new about the events"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("EventUpdate", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



    private inline fun <reified T:Fragment> changeFragment(){
        val fragments = fragmentManager.fragments
        if (fragments[0] !is T) {
            fragmentManager.commit {
                setReorderingAllowed(true)
                replace<T>(R.id.fragment_container_view)
            }
        }
    }

    fun openEventActivity(view :View){
        val eventId = view.tag as Int
        val intent = Intent(this, EventActivity::class.java).apply {
            putExtra("event_id", eventId)
        }

        startActivity(intent)
    }

    fun openChampionshipActivity(view : View){
        val championshipId = view.tag as Int
        val intent = Intent(this, ChampionshipActivity::class.java).apply {
            putExtra("championship_id", championshipId)
        }
        startActivity(intent)
    }

    fun openTrackActivity(view : View){
        val trackId = view.tag as Int
        val intent = Intent(this, TrackActivity::class.java).apply {
            putExtra("track_id", trackId)
        }

        startActivity(intent)
    }

    fun openNewsActivity(view : View){
        val newsId = view.tag as Int
        val intent = Intent(this, NewsActivity::class.java).apply {
            putExtra("news_id", newsId)
        }
        startActivity(intent)
    }

    fun openNearbyEventsActivity(view : View){
        val intent = Intent(this, NearbyEventsActivity::class.java)
        startActivity(intent)
    }

    fun onTrackFollowButtonClick(view : View){
        runBlocking {
            launch {
                tracksViewModel.changeFollowed(view.tag as Int)
            }
        }

    }

    fun onChampionshipFollowButtonClick(view : View){
        runBlocking {
            launch {
                championshipsViewModel.changeFollowed(view.tag as Int)
            }
        }
    }



    private fun setBottomNavBehaviour(activity: Activity){
        val bottomNavigation : BottomNavigationView = activity.findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.home_bottom_btn -> {
                    changeFragment<HomeFragment>()
                    true
                }
                R.id.favourites_bottom_btn -> {
                    changeFragment<FavouritesEventFragment>()
                    true
                }
                R.id.discover_bottom_btn -> {
                    changeFragment<DiscoverFragment>()
                    true
                }
                R.id.news_bottom_btn -> {
                    changeFragment<NewsFragment>()
                    true
                }
                else -> false
            }
        }
    }
}