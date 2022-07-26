package com.example.motorsportspotter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.event.EventDetailFragmentViewModel

class EventActivity : AppCompatActivity() {

    private val fragmentViewModel : EventDetailFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setEventId(intent.getIntExtra("event_id", 0))
        setContentView(R.layout.event_activity)
    }
}