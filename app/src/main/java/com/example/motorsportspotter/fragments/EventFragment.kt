package com.example.motorsportspotter.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

class EventFragment : Fragment() {
    lateinit var adapter : EventCardAdapter
    private lateinit var followedEventsRecyclerView: RecyclerView
    private lateinit var followedTracksRecyclerView: RecyclerView
    private lateinit var followedChampRecyclerView: RecyclerView
    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }
    private val eventConverter = DBEntitiesConvertersFactory.getEventsConverter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(this.activity != null){
            setRecyclerView(view)
            eventViewModel.allEvents.observe(viewLifecycleOwner) { events ->
                // Update the cached copy of the words in the adapter.
                events.let { adapter.updateList(eventConverter.convertAll(it)) }
            }
        } else {
            Log.e("ERROR", "activity is null")
        }
    }


    private fun setRecyclerView(view: View){
        followedEventsRecyclerView = view.findViewById(R.id.home_events_recycler_view)
        followedTracksRecyclerView = view.findViewById(R.id.home_tracks_recycler_view)
        followedChampRecyclerView = view.findViewById(R.id.home_championships_recycler_view)
        val events = ArrayList<Event>()
        Collections.addAll(events, Event("24 Hours Of LeMans","24 Febbraio","Circuit De La Sharthe","","WEC 2022"),
            Event("24 Hours Of LeMans","24 Febbraio","Circuit De La Sharthe","","WEC 2022"),
            Event("24 Hours Of LeMans","24 Febbraio","Circuit De La Sharthe","","WEC 2022"),)
        adapter = EventCardAdapter(events, requireActivity())
        followedEventsRecyclerView.adapter = adapter
        followedTracksRecyclerView.adapter = adapter
        followedChampRecyclerView.adapter = adapter
    }
}

