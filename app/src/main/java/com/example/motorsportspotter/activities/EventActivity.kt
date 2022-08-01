package com.example.motorsportspotter.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.event.EventDetailFragmentViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EventActivity : AppCompatActivity() {

    private val fragmentViewModel : EventDetailFragmentViewModel by viewModels()

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((application as EventsApplication).championshipRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setEventId(intent.getIntExtra("event_id", 0))
        setContentView(R.layout.event_activity)
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

}