package com.example.motorsportspotter.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.adapters.*
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.databinding.HomeFragmentBinding
import com.example.motorsportspotter.database.viewmodel.*

class HomeFragment : Fragment() {

    companion object{
        val Instance = HomeFragment()
    }

    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((this.activity?.application as EventsApplication).championshipRepository)
    }

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((requireActivity().application as EventsApplication).tracksRepository)
    }

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.eventViewModel = eventViewModel
        binding.championshipViewModel = championshipsViewModel
        binding.trackViewModel = tracksViewModel

        binding.homeFragmentRw.adapter = EventCardsAdaptersFactory.getEventCardAdapter()
        binding.suggestedTracksRw.adapter = TrackCardsAdaptersFactory.getTrackCardAdapter()
        binding.suggestedChampionshipsRw.adapter = ChampionshipCardAdaptersFactory.getChampionshipCardAdapter()

        return binding.root
    }

}