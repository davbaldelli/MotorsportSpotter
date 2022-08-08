package com.example.motorsportspotter.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.event.EventDetailFragmentViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EventActivity : AppCompatActivity() {

    private val fragmentViewModel : EventDetailFragmentViewModel by viewModels()

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((application as EventsApplication).championshipRepository)
    }

    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.application as EventsApplication).eventRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setEventId(intent.getIntExtra("event_id", 0))
        setContentView(R.layout.event_activity)

        val actionBar: MaterialToolbar = findViewById(R.id.event_toolbar)
        setSupportActionBar(actionBar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.event_appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.follow -> {
                runBlocking {
                    launch {
                        eventViewModel.setFavourite(intent.getIntExtra("event_id", 0))
                    }
                }
                true
            }
            R.id.share -> {
                // save profile changes
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    fun openEventActivity(view :View){
        val eventId = view.tag as Int
        val intent = Intent(this, EventActivity::class.java).apply {
            putExtra("event_id", eventId)
        }

        startActivity(intent)
    }

    fun onChampionshipFollowButtonClick(view : View){
        runBlocking {
            launch {
                championshipsViewModel.changeFollowed(view.tag as Int)
            }
        }
    }

    fun onBuyTicketsClick(view : View){
        goToUrl(view.tag as String)
    }

    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }

}