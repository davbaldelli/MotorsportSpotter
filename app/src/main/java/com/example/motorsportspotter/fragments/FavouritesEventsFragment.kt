package com.example.motorsportspotter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory

import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory

class FavouritesEventFragment : Fragment() {

    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resultView = view.findViewById<RecyclerView>(R.id.fav_events_rw)
        val adapter = EventCardAdapter()
        resultView.adapter = adapter
        eventViewModel.allEvents.observe(viewLifecycleOwner) { events -> events.let { adapter.submitList(DBEntitiesConvertersFactory.getEventsConverter().convertAll(it)) } }
    }
}