package com.example.motorsportspotter.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.components.recyclerviews.adapters.ChampionshipsCardsAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.TrackCardsAdapter
import com.example.motorsportspotter.databinding.HomeFragmentBinding
import com.example.motorsportspotter.room.viewmodel.*

class HomeFragment : Fragment() {

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

        binding.homeFragmentRw.adapter = EventCardAdapter(this)
        binding.suggestedTracksRw.adapter = TrackCardsAdapter(this)
        binding.suggestedChampionshipsRw.adapter = ChampionshipsCardsAdapter(this)

        return binding.root
    }

}