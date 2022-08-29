package com.example.motorsportspotter.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.entities.Championship
import com.example.motorsportspotter.components.recyclerviews.entities.Track
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.entities.Event
import com.example.motorsportspotter.room.viewmodel.*
import com.example.motorsportspotter.services.retrofit.RemoteClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    private val championshipsViewModel: ChampionshipsViewModel by viewModels {
        ChampionshipsViewModelFactory((requireActivity().application as EventsApplication).championshipRepository)
    }

    private val tracksViewModel: TracksViewModel by viewModels {
        TracksViewModelFactory((requireActivity().application as EventsApplication).tracksRepository)
    }

    private val eventViewModel: EventsViewModel by viewModels {
        EventsViewModelFactory((this.activity?.application as EventsApplication).eventRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val service = RemoteClient.getRemoteService()
        service.allChampionships()?.enqueue(object : Callback<List<Championship>> {
            override fun onResponse(call: Call<List<Championship>>, response: Response<List<Championship>>) {
                response.body()?.let {
                    DBEntitiesConvertersFactory.ToDBChampConverter.convertAll(it).forEach { champ ->
                        championshipsViewModel.insert(champ)
                    }
                }
            }

            override fun onFailure(call: Call<List<Championship>>, t: Throwable) {
                Log.e("ERROR", t.toString())
            }
        })

        service.allTracks()?.enqueue(object : Callback<List<Track>> {
            override fun onResponse(call: Call<List<Track>>, response: Response<List<Track>>) {
                response.body()?.let {
                    DBEntitiesConvertersFactory.ToDBTrackConverter.convertAll(it).forEach { track ->
                        tracksViewModel.insert(track)
                    }
                }
            }

            override fun onFailure(call: Call<List<Track>>, t: Throwable) {
                //TODO("Not yet implemented")
            }

        })

        service.allEvents()?.enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                response.body()?.let {
                    it.forEach { event ->
                        eventViewModel.insert(event)
                    }
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                //TODO("Not yet implemented")
            }

        })

        return inflater.inflate(R.layout.news_fragment, container, false)
    }
}