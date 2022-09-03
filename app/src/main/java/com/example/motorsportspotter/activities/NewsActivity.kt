package com.example.motorsportspotter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.news.NewsFragmentViewModel
import com.google.android.material.appbar.MaterialToolbar

class NewsActivity : AppCompatActivity() {

    private val fragmentViewModel : NewsFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.setNewsId(intent.getIntExtra("news_id", 0))
        setContentView(R.layout.news_activity)
        val actionBar: MaterialToolbar = findViewById(R.id.event_toolbar)
        setSupportActionBar(actionBar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}