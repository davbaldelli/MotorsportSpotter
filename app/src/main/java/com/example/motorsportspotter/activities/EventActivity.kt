package com.example.motorsportspotter.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.event.EventDetailFragmentViewModel
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.database.viewmodel.EventsViewModel
import com.example.motorsportspotter.database.viewmodel.EventsViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        setActionBarFollowIcon(menu.getItem(0))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.follow -> {
                val id = intent.getIntExtra("event_id", 0)
                CoroutineScope(Dispatchers.IO).launch {
                    eventViewModel.setFavourite(id)
                    setActionBarFollowIcon(item)
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

    private fun setActionBarFollowIcon(item : MenuItem){
        val id = intent.getIntExtra("event_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val event = eventViewModel.getByIdSync(id)
            withContext(Dispatchers.Main){
                if (event.event.favourites) {
                    item.setIcon(R.drawable.ic_favorite_white_24)
                } else {
                    item.setIcon(R.drawable.ic_not_favorite_white_24)
                }
            }
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
        championshipsViewModel.changeFollowed(view.tag as Int)
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