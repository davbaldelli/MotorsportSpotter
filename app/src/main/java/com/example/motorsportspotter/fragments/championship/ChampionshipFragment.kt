package com.example.motorsportspotter.fragments.championship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.adapters.ChampionshipEventCardAdapter
import com.example.motorsportspotter.databinding.ChampionshipFragmentBinding
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.room.viewmodel.EventsViewModel
import com.example.motorsportspotter.room.viewmodel.EventsViewModelFactory
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converters

class ChampionshipFragment : Fragment() {

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((this.activity?.application as EventsApplication).championshipRepository)
    }

    private val eventsViewModel : EventsViewModel by viewModels {
        EventsViewModelFactory((requireActivity().application as EventsApplication).eventRepository)
    }

    private val viewModel : ChampionshipFragmentViewModel by activityViewModels()

    private lateinit var binding: ChampionshipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChampionshipFragmentBinding.inflate(inflater)
        binding.ongoingCampEventsRw.adapter = ChampionshipEventCardAdapter()
        binding.futureCampEventsRw.adapter = ChampionshipEventCardAdapter()
        binding.pastCampEventsRw.adapter = ChampionshipEventCardAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.championshipId.observe(viewLifecycleOwner) { championshipId ->
            championshipsViewModel.getChampionshipById(championshipId).observe(viewLifecycleOwner){
                binding.championship = Converters.ChampionshipsConverter.convertAll(listOf(it))[0]
                eventsViewModel.ongoingChampionshipEvents(championshipId).observe(viewLifecycleOwner) { ongoingEvents ->
                    binding.ongoingEvents = Converters.CompleteEventConverter.convertAll(ongoingEvents)
                }
                eventsViewModel.futureChampionshipEvents(championshipId).observe(viewLifecycleOwner) { futureEvents ->
                    binding.futureEvents = Converters.CompleteEventConverter.convertAll(futureEvents)
                }
                eventsViewModel.pastChampionshipEvents(championshipId).observe(viewLifecycleOwner) { pastEvents ->
                    binding.pastEvents = Converters.CompleteEventConverter.convertAll(pastEvents)
                }
            }
        }

    }


}