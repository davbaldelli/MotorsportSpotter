package com.example.motorsportspotter.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.databinding.FavouriteAllFragmentBinding
import com.example.motorsportspotter.databinding.FavouriteEventsFragmentBinding
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory

class FavouriteAllFragment : Fragment() {

    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }

    private lateinit var binding : FavouriteAllFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FavouriteAllFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = eventViewModel
        binding.favEventsRw.adapter = EventCardAdapter()
        return binding.root
    }
}