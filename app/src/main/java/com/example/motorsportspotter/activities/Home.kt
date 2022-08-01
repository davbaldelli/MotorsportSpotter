package com.example.motorsportspotter.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.home.*
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.room.viewmodel.TracksViewModel
import com.example.motorsportspotter.room.viewmodel.TracksViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.coroutineScope
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
                    changeFragment<HomeFragmentX>()
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
                R.id.notifications_bottom_btn -> {
                    Toast.makeText(applicationContext, "notifications", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}