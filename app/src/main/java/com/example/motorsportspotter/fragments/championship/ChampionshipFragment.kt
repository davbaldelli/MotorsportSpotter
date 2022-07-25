package com.example.motorsportspotter.fragments.championship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.databinding.ChampionshipFragmentBinding
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
        val adapter = EventCardAdapter()
        resultView.adapter = adapter
        championshipsViewModel.allChampionships.observe(viewLifecycleOwner){championships ->
            val championship = DBEntitiesConvertersFactory.getChampionshipsConverter().convertAll(championships)[0]
            binding.championship = championship
            eventViewModel.getByChampionship(championships[0].championship.uid).observe(viewLifecycleOwner){ events ->
                adapter.submitList(
                    DBEntitiesConvertersFactory.getEventsConverter().convertAll(events)
                )
            }

        }
    }


}