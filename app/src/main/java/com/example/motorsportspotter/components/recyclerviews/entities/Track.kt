package com.example.motorsportspotter.components.recyclerviews.entities

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.motorsportspotter.activities.TrackActivity

class Track(
    val id : Int,
    val name : String,
    val coordinates : Pair<String, String>?,
    val backgroundUrl : String,
    val logoUrl : String,
    val followed : Boolean,
    val location : String,
    ) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getDescription(): String {
        return "Circuit"
    }

    override fun getImgRes(): String {
        return backgroundUrl
    }

    override fun onClick(view: View) {
        val activity : Activity = view.context as Activity
        activity.apply {
            val intent = Intent(this, TrackActivity::class.java).apply {
                putExtra("track_id", id)
            }

            startActivity(intent)
        }
    }
}