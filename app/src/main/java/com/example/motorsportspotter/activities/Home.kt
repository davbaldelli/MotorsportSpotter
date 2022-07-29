package com.example.motorsportspotter.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.motorsportspotter.R
import com.example.motorsportspotter.fragments.home.DiscoverFragment
import com.example.motorsportspotter.fragments.home.FavouritesEventFragment
import com.example.motorsportspotter.fragments.home.HomeFragment
import com.example.motorsportspotter.fragments.home.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class Home : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setBottomNavBehaviour(this)
    }


    private inline fun <reified T:Fragment> changeFragment(){
        val fragments = fragmentManager.fragments
        if (fragments[0] !is T) {
            fragmentManager.commit {
                setReorderingAllowed(true)
                replace<T>(R.id.fragment_container_view)
            }
        }
    }

    fun openEventActivity(view :View){
        val eventId = view.tag as Int
        val intent = Intent(this, EventActivity::class.java).apply {
            putExtra("event_id", eventId)
        }

        startActivity(intent)
    }


    private fun setBottomNavBehaviour(activity: Activity){
        val bottomNavigation : BottomNavigationView = activity.findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.home_bottom_btn -> {
                    changeFragment<HomeFragment>()
                    true
                }
                R.id.favourites_bottom_btn -> {
                    changeFragment<FavouritesEventFragment>()
                    true
                }
                R.id.discover_bottom_btn -> {
                    changeFragment<DiscoverFragment>()
                    true
                }
                R.id.news_bottom_btn -> {
                    changeFragment<NewsFragment>()
                    true
                }
                R.id.notifications_bottom_btn -> {
                    Toast.makeText(applicationContext, "notifications", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}