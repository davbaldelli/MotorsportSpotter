package com.example.motorsportspotter.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.track.TrackFragmentViewModel

class TrackActivity : AppCompatActivity() {

    val viewModel : TrackFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTrackId(intent.getIntExtra("track_id",0))
        setContentView(R.layout.track_activity)
    }
}