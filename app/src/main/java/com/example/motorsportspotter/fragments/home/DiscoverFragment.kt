package com.example.motorsportspotter.fragments.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.services.EventsApplication
import com.example.motorsportspotter.adapters.EventSearchResultAdapter
import com.example.motorsportspotter.models.Championship
import com.example.motorsportspotter.models.Event
import com.example.motorsportspotter.models.SearchResult
import com.example.motorsportspotter.models.Track
import com.example.motorsportspotter.databinding.DiscoverFragmentBinding
import com.example.motorsportspotter.room.viewmodel.*
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converter

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
    private val tracksConverter = Converter.TracksConverter
    private val championshipConverter = Converter.ChampionshipsConverter
    private val eventConverter = Converter.EventConverter
    private var tracks = ArrayList<Track>()
    private var championships = ArrayList<Championship>()
    private var events = ArrayList<Event>()
    private lateinit var binding : DiscoverFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DiscoverFragmentBinding.inflate(inflater)
        return binding.root
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
            setupRecyclerView(requireActivity())
            setupSearchBar(requireActivity())
        }
    }

    private fun setupRecyclerView(activity: Activity){
        binding.searchResultList.adapter = EventSearchResultAdapter()
    }

    private fun setupSearchBar(activity: Activity){
        val searchBar = binding.discoverSearchBar
        val adapter = binding.searchResultList.adapter as EventSearchResultAdapter
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null && query != ""){
                    adapter.submitList(searchFromAll(query))
                } else {
                    adapter.submitList(ArrayList())
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null && query != ""){
                    adapter.submitList(searchFromAll(query))
                } else {
                    adapter.submitList(ArrayList())
                }
                return true
            }

        })
    }


    private fun searchFromTracks(queryString : String) : List<Track>{
        return tracks.filter { it.matchSearchQuery { value -> value.lowercase().contains(queryString.lowercase()) } }
    }

    private fun searchFromEvents(queryString : String) : List<Event>{
        return events.filter { it.matchSearchQuery { value -> value.lowercase().contains(queryString.lowercase()) } }
    }

    private fun searchFromChampionships(queryString : String) : List<Championship>{
        return championships.filter { it.matchSearchQuery { value -> value.lowercase().contains(queryString.lowercase()) } }
    }


    private fun searchFromAll(queryString: String) : List<SearchResult>{
        val items = ArrayList<SearchResult>()
        items.addAll(searchFromTracks(queryString))
        items.addAll(searchFromEvents(queryString))
        items.addAll(searchFromChampionships(queryString))
        return items
    }
}