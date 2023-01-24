package com.example.motorsportspotter.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.adapters.TrackCardsAdaptersFactory
import com.example.motorsportspotter.databinding.FavouritesTracksFragmentBinding
import com.example.motorsportspotter.database.viewmodel.TracksViewModel
import com.example.motorsportspotter.database.viewmodel.TracksViewModelFactory

class FavouritesTracksFragment : Fragment() {

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((requireActivity().application as EventsApplication).tracksRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FavouritesTracksFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = tracksViewModel
        binding.favTracksRw.adapter = TrackCardsAdaptersFactory.getHorizontalTrackCardAdapter()

        return binding.root
    }
}