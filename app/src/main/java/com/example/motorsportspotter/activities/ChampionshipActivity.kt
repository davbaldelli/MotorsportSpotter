package com.example.motorsportspotter.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.championship.ChampionshipFragmentViewModel

class ChampionshipActivity : AppCompatActivity() {

    private val fragmentViewModel : ChampionshipFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setChampionshipId(intent.getIntExtra("championship_id",0))
        setContentView(R.layout.championship_activity)
    }

    fun openEventActivity(view : View){
        val eventId = view.tag as Int
        val intent = Intent(this, EventActivity::class.java).apply {
            putExtra("event_id", eventId)
        }

        startActivity(intent)
    }


}