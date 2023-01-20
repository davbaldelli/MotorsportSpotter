package com.example.motorsportspotter.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.adapters.EventCardAdapter
import com.example.motorsportspotter.databinding.FavouriteAllFragmentBinding
import com.example.motorsportspotter.database.viewmodel.EventsViewModel
import com.example.motorsportspotter.database.viewmodel.EventsViewModelFactory

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
        binding.favEventsRw.adapter = EventCardAdapter(this)
        return binding.root
    }

}