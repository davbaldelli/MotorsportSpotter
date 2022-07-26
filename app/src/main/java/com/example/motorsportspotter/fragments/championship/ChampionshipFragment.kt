package com.example.motorsportspotter.fragments.championship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.ChampionshipEventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.databinding.ChampionshipFragmentBinding
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converter
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory

class ChampionshipFragment : Fragment() {

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((this.activity?.application as EventsApplication).championshipRepository)
    }
    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }

    private lateinit var binding: ChampionshipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChampionshipFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resultView = view.findViewById<RecyclerView>(R.id.champ_events_rw)
        val adapter = ChampionshipEventCardAdapter()
        resultView.adapter = adapter
        championshipsViewModel.getChampionshipById(3).observe(viewLifecycleOwner){it ->
            val championship = Converter.ChampionshipsConverter.convertAll(
                listOf(it))[0]
            binding.championship = championship
            adapter.submitList(Converter.ChampionshipEventConverter.convertAll(it.events))
        }
    }


}