package com.example.motorsportspotter.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.motorsportspotter.EventsApplication
import com.example.motorsportspotter.R
import com.example.motorsportspotter.databinding.NewsDetailFragmentBinding
import com.example.motorsportspotter.fragments.track.TrackFragmentViewModel
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.viewmodel.NewsViewModel
import com.example.motorsportspotter.room.viewmodel.NewsViewModelFactory

class NewsDetailFragment : Fragment() {

    private val activityViewModel: NewsFragmentViewModel by activityViewModels()

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory((this.activity?.application as EventsApplication).newsRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = NewsDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        activityViewModel.newsId.observe(viewLifecycleOwner) { id ->
            newsViewModel.getById(id).observe(viewLifecycleOwner) { news ->
                binding.news = DBEntitiesConvertersFactory.NewsConverter.convertAll(listOf(news))[0]
            }
        }

        return binding.root
    }

}