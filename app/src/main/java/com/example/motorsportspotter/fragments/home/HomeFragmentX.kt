package com.example.motorsportspotter.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.ChampionshipsCardsAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.TrackCardsAdapter
import com.example.motorsportspotter.databinding.FavouriteEventsFragmentBinding
import com.example.motorsportspotter.databinding.HomeFragmentBinding
import com.example.motorsportspotter.room.viewmodel.*

import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converters

class HomeFragmentX : Fragment() {

    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((this.activity?.application as EventsApplication).championshipRepository)
    }

    private val tracksViewModel : TracksViewModel by viewModels {
        TracksViewModelFactory((requireActivity().application as EventsApplication).tracksRepository)
    }

    private lateinit var binding : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.eventViewModel = eventViewModel
        binding.championshipViewModel = championshipsViewModel
        binding.trackViewModel = tracksViewModel
        binding.homeFragmentRw.adapter = EventCardAdapter()
        binding.suggestedTracksRw.adapter = TrackCardsAdapter()
        binding.suggestedChampionshipsRw.adapter = ChampionshipsCardsAdapter()
        return binding.root
    }

    fun favButtonClick(view : View){
        eventViewModel.setFavourite(view.tag as Int)
    }
}