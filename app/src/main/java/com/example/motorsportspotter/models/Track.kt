package com.example.motorsportspotter.models

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.motorsportspotter.activities.TrackActivity
import com.google.gson.annotations.SerializedName

class Track(
    val id : Int,
    val name : String,
    val commonName : String,
    @SerializedName("image")
    val backgroundUrl : String,
    @SerializedName("logo")
    val logoUrl : String,
    val followed : Boolean,
    val location : String,
    val nationCode : String,
    ) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name+commonName)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getDescription(): String {
        return "Circuit"
    }

    override fun getImgRes(): String {
        return logoUrl
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