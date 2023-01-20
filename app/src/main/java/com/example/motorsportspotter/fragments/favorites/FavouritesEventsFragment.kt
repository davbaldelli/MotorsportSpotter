package com.example.motorsportspotter.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.motorsportspotter.databinding.FavouriteEventsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavouritesEventFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FavouriteEventsFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.favViewPager.adapter = FavouritesPagerAdapter(this)

        TabLayoutMediator(binding.favTabLayout, binding.favViewPager) { tab, position ->
            when(position){
                0 -> tab.text = "All"
                1 -> tab.text = "Championships"
                2 -> tab.text = "Tracks"
            }
        }.attach()

        return binding.root
    }

}

class FavouritesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragments = listOf(FavouriteAllFragment(), FavouritesChampionshipsFragment(), FavouritesTracksFragment())
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}
