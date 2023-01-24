package com.example.motorsportspotter.fragments.championship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.adapters.EventCardsAdaptersFactory
import com.example.motorsportspotter.database.EventsApplication
import com.example.motorsportspotter.databinding.ChampionshipFragmentBinding
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModel
import com.example.motorsportspotter.database.viewmodel.ChampionshipsViewModelFactory
import com.example.motorsportspotter.database.viewmodel.EventsViewModel
import com.example.motorsportspotter.database.viewmodel.EventsViewModelFactory
import com.example.motorsportspotter.database.entities.DBEntitiesConvertersFactory as Converters

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
        binding.ongoingCampEventsRw.adapter = EventCardsAdaptersFactory.getChampionshipEventCardAdapter()
        binding.futureCampEventsRw.adapter = EventCardsAdaptersFactory.getChampionshipEventCardAdapter()
        binding.pastCampEventsRw.adapter = EventCardsAdaptersFactory.getChampionshipEventCardAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.championshipId.observe(viewLifecycleOwner) { championshipId ->
            championshipsViewModel.getChampionshipById(championshipId).observe(viewLifecycleOwner){
                binding.championship = Converters.ChampionshipsConverter.convertAll(listOf(it))[0]
                eventsViewModel.ongoingChampionshipEvents(championshipId).observe(viewLifecycleOwner) { ongoingEvents ->
                    if(ongoingEvents.isEmpty()){
                        binding.ongoingEventsLabel.visibility = View.GONE
                    }
                    binding.ongoingEvents = Converters.EventConverter.convertAll(ongoingEvents)
                    binding.executePendingBindings()
                }
                eventsViewModel.futureChampionshipEvents(championshipId).observe(viewLifecycleOwner) { futureEvents ->
                    if(futureEvents.isEmpty()){
                        binding.futureEventsLabel.visibility = View.GONE
                    }
                    binding.futureEvents = Converters.EventConverter.convertAll(futureEvents)
                    binding.executePendingBindings()
                }
                eventsViewModel.pastChampionshipEvents(championshipId).observe(viewLifecycleOwner) { pastEvents ->
                    if(pastEvents.isEmpty()){
                        binding.pastEventsLabel.visibility = View.GONE
                    }
                    binding.pastEvents = Converters.EventConverter.convertAll(pastEvents)
                    binding.executePendingBindings()
                }
            }
        }

    }


}