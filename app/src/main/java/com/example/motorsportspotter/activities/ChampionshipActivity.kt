package com.example.motorsportspotter.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.championship.ChampionshipFragmentViewModel

class ChampionshipActivity : AppCompatActivity() {

    val fragmentViewModel : ChampionshipFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setChampionshipId(intent.getIntExtra("championship_id",0))
        setContentView(R.layout.championship_activity)
    }


}