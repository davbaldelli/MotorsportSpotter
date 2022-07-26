package com.example.motorsportspotter.fragments.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.TrackEventsAdapter
import com.example.motorsportspotter.databinding.TrackFragmentBinding
import com.example.motorsportspotter.room.viewmodel.TracksViewModel
import com.example.motorsportspotter.room.viewmodel.TracksViewModelFactory
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converters

class TrackFragment : Fragment() {

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((requireActivity().application as EventsApplication).tracksRepository)
    }
    private lateinit var binding: TrackFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = TrackFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resultView = view.findViewById<RecyclerView>(R.id.track_events_rw)
        val adapter = TrackEventsAdapter()
        resultView.adapter = adapter
        tracksViewModel.getTrack(0).observe(viewLifecycleOwner) { track ->
            binding.track = Converters.TracksConverter.convertAll(listOf(track))[0]
            adapter.submitList(Converters.TrackEventConverter.convertAll(track.events))
        }
    }

}