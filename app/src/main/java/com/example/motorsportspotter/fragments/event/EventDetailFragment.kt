package com.example.motorsportspotter.fragments.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.databinding.EventDetailFragmentBinding
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory

class EventDetailFragment : Fragment() {

    val viewModel : EventDetailFragmentViewModel by activityViewModels()
    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }
    private lateinit var binding : EventDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = EventDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.eventId.observe(viewLifecycleOwner) {id ->
            eventViewModel.getById(id).observe(viewLifecycleOwner) { event ->
                binding.event = DBEntitiesConvertersFactory.CompleteEventConverter.convertAll(listOf(event))[0]
                binding.executePendingBindings()
            }
        }
        return binding.root
    }

}