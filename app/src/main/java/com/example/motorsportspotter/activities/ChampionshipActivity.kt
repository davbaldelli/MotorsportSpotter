package com.example.motorsportspotter.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.championship.ChampionshipFragmentViewModel
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChampionshipActivity : AppCompatActivity() {

    private val fragmentViewModel : ChampionshipFragmentViewModel by viewModels()

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((application as EventsApplication).championshipRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setChampionshipId(intent.getIntExtra("championship_id",0))
        setContentView(R.layout.championship_activity)
        val actionBar: MaterialToolbar = findViewById(R.id.event_toolbar)
        setSupportActionBar(actionBar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun openEventActivity(view : View){
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