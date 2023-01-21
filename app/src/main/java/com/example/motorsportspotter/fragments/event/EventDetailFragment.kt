package com.example.motorsportspotter.fragments.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.adapters.VerticalEventCardAdapter
import com.example.motorsportspotter.databinding.EventDetailFragmentBinding
import com.example.motorsportspotter.database.viewmodel.EventsViewModel
import com.example.motorsportspotter.database.viewmodel.EventsViewModelFactory
import com.example.motorsportspotter.database.entities.DBEntitiesConvertersFactory as Converters

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
        binding.similarEventsRw.adapter = VerticalEventCardAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.eventId.observe(viewLifecycleOwner) {id ->
            eventViewModel.getById(id).observe(viewLifecycleOwner) { event ->
                binding.event = Converters.EventConverter.convertAll(listOf(event))[0]
                (activity as AppCompatActivity).supportActionBar
                eventViewModel.getSimilarEvents(event.championship.id, event.track.id, event.event.id).observe(viewLifecycleOwner) { similarEvents ->
                    binding.similarEvents = Converters.EventConverter.convertAll(similarEvents)
                }
            }

        }
        return binding.root
    }

}