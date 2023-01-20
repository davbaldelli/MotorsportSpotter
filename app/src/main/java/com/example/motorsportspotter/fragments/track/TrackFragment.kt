package com.example.motorsportspotter.fragments.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.adapters.TrackEventsAdapter
import com.example.motorsportspotter.databinding.TrackFragmentBinding
import com.example.motorsportspotter.database.viewmodel.EventsViewModel
import com.example.motorsportspotter.database.viewmodel.EventsViewModelFactory
import com.example.motorsportspotter.database.viewmodel.TracksViewModel
import com.example.motorsportspotter.database.viewmodel.TracksViewModelFactory
import com.example.motorsportspotter.database.entities.DBEntitiesConvertersFactory as Converters

class TrackFragment : Fragment() {

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((requireActivity().application as EventsApplication).tracksRepository)
    }

    private val eventsViewModel : EventsViewModel by viewModels {
        EventsViewModelFactory((requireActivity().application as EventsApplication).eventRepository)
    }

    private val activityViewModel: TrackFragmentViewModel by activityViewModels()

    private lateinit var binding: TrackFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = TrackFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.ongoingCampEventsRw.adapter = TrackEventsAdapter()
        binding.futureCampEventsRw.adapter = TrackEventsAdapter()
        binding.pastCampEventsRw.adapter = TrackEventsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activityViewModel.trackId.observe(viewLifecycleOwner) { trackId ->
            tracksViewModel.getTrack(trackId).observe(viewLifecycleOwner) { track ->
                binding.track = Converters.TracksConverter.convertAll(listOf(track))[0]
                eventsViewModel.ongoingTrackEvents(trackId).observe(viewLifecycleOwner) { ongoingEvents ->
                    if(ongoingEvents.isEmpty()){
                        binding.ongoingEventsLabel.visibility = View.GONE
                    }
                    binding.ongoingEvents = Converters.EventConverter.convertAll(ongoingEvents)
                }
                eventsViewModel.futureTrackEvents(trackId).observe(viewLifecycleOwner) { futureEvents ->
                    if(futureEvents.isEmpty()){
                        binding.futureEventsLabel.visibility = View.GONE
                    }
                    binding.futureEvents = Converters.EventConverter.convertAll(futureEvents)
                }
                eventsViewModel.pastTrackEvents(trackId).observe(viewLifecycleOwner) { pastEvents ->
                    if(pastEvents.isEmpty()){
                        binding.pastEventsLabel.visibility = View.GONE
                    }
                    binding.pastEvents = Converters.EventConverter.convertAll(pastEvents)
                }
            }
        }
    }

}