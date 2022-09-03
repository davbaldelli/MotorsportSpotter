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
import com.example.motorsportspotter.components.recyclerviews.adapters.NewsCardAdapter
import com.example.motorsportspotter.databinding.NewsFragmentBinding
import com.example.motorsportspotter.room.entities.*
import com.example.motorsportspotter.room.viewmodel.*
import com.example.motorsportspotter.services.retrofit.RemoteClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory((this.activity?.application as EventsApplication).newsRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = NewsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = newsViewModel

        binding.newsRw.adapter = NewsCardAdapter()

        return binding.root
    }
}