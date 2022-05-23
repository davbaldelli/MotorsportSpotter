package com.example.motorsportspotter.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.entities.Championship
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.components.recyclerviews.entities.Track
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.viewmodel.*
import kotlin.properties.Delegates

class DiscoverFragment : Fragment() {

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((this.activity?.application as EventsApplication).tracksRepository)
    }
    private val championshipsViewModel : ChampionshipsViewModel  by viewModels {
        ChampionshipsViewModelFactory((this.activity?.application as EventsApplication).championshipRepository)
    }
    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }
    private val tracksConverter = DBEntitiesConvertersFactory.getTracksConverter()
    private val championshipConverter = DBEntitiesConvertersFactory.getChampionshipsConverter()
    private val eventConverter = DBEntitiesConvertersFactory.getEventsConverter()
    private var tracks = ArrayList<Track>()
    private var championships = ArrayList<Championship>()
    private var events = ArrayList<Event>()
    private lateinit var searchBar : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.discover_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(this.activity != null){
            tracksViewModel.allTracks.observe(viewLifecycleOwner) {items ->
                items.let { this.tracks = tracksConverter.convertAll(it) as ArrayList<Track> }
            }
            championshipsViewModel.allChampionships.observe(viewLifecycleOwner) {items ->
                items.let { this.championships = championshipConverter.convertAll(it) as ArrayList<Championship>}
            }
            eventViewModel.allEvents.observe(viewLifecycleOwner) {
                items -> items.let { this.events = eventConverter.convertAll(it)  as ArrayList<Event> }
            }
            setupSearchBar(this.requireActivity())
        }
    }

    private fun setupSearchBar(activity: Activity){
        searchBar = activity.findViewById(R.id.discover_search_bar)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    refreshTracksResultList(searchFromTracks(query))
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    refreshTracksResultList(searchFromTracks(query))
                }
                return true
            }

        })
    }

    private fun refreshTracksResultList(tracks : List<Track>){
        Toast.makeText(this.requireActivity().applicationContext, tracks.size.toString(), Toast.LENGTH_SHORT).show()
    }


    private fun searchFromTracks(queryString : String) : List<Track>{
        return tracks.filter { it.matchSearchQuery { value -> value.contains(queryString) } }
    }

    private fun searchFromEvents(queryString : String) : List<Event>{
        return events.filter { it.matchSearchQuery { value -> value.contains(queryString) } }
    }

    private fun searchFromChampionships(queryString : String) : List<Championship>{
        return championships.filter { it.matchSearchQuery { value -> value.contains(queryString) } }
    }

    private fun searchFromAll(queryString: String) : Triple<List<Track>, List<Event>, List<Championship>>{
        return Triple(searchFromTracks(queryString), searchFromEvents(queryString), searchFromChampionships(queryString))
    }
}