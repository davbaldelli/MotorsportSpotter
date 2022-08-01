package com.example.motorsportspotter.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.track.TrackFragmentViewModel
import com.example.motorsportspotter.room.viewmodel.TracksViewModel
import com.example.motorsportspotter.room.viewmodel.TracksViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TrackActivity : AppCompatActivity() {

    val viewModel : TrackFragmentViewModel by viewModels()

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((application as EventsApplication).tracksRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTrackId(intent.getIntExtra("track_id",0))
        setContentView(R.layout.track_activity)
    }

    fun openEventActivity(view : View){
        val eventId = view.tag as Int
        val intent = Intent(this, EventActivity::class.java).apply {
            putExtra("event_id", eventId)
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
}