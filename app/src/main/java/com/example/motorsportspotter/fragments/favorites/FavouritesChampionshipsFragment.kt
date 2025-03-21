package com.example.motorsportspotter.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.adapters.ChampionshipCardAdaptersFactory
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.databinding.FavouritesChampionshipsFragmentBinding
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModelFactory

class FavouritesChampionshipsFragment : Fragment() {

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((requireActivity().application as EventsApplication).championshipRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FavouritesChampionshipsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel =  championshipsViewModel
        binding.followedChampionshipsRw.adapter = ChampionshipCardAdaptersFactory.getHorizontalChampionshipCardAdapter()

        return binding.root
    }
}