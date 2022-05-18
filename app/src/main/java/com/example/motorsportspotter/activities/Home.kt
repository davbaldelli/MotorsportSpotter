package com.example.motorsportspotter.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.CardAdapter
import com.example.motorsportspotter.components.recyclerviews.Event
import java.util.ArrayList

class Home : AppCompatActivity() {

    lateinit var adapter : CardAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setRecyclerView(this)
    }

    private fun setRecyclerView(activity : Activity){
        recyclerView = activity.findViewById(R.id.home_recycler_view)
        recyclerView.setHasFixedSize(true)
        val events = ArrayList<Event>()
        events.add(Event("24 Hours Of LeMans","24 Febbraio","Circuit De La Sharthe","","WEC 2022"))
        adapter = CardAdapter(events, activity)
        recyclerView.adapter = adapter
    }
}