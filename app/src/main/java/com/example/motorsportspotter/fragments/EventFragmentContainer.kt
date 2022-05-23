package com.example.motorsportspotter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.motorsportspotter.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*
import kotlin.collections.ArrayList

class EventFragmentContainer : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.event_container_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = EventFragmentsPagerAdapter(this)
        val viewPager : ViewPager2 = view.findViewById(R.id.events_view_pager)
        viewPager.adapter = pagerAdapter
        val tabLayout : TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.fragments[position].first
        }.attach()

    }

}

class EventFragmentsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragments : List<Pair<String, EventFragment>> = listOf(Pair("Favourites", EventFragment()), Pair("Popular", EventFragment()))

    override fun getItemCount(): Int {
        return fragments.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragments[position].second
    }

}