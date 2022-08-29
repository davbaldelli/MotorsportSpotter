package com.example.motorsportspotter.components.recyclerviews.entities

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.motorsportspotter.activities.ChampionshipActivity
import com.google.gson.annotations.SerializedName

class Championship(
    val id : Int,
    val name: String,
    val year : Int,
    @SerializedName("pretty_name")
    val prettyName : String,
    @SerializedName("background_url")
    val backgroundUrl : String,
    @SerializedName("logo_url")
    val logoUrl : String,
    val followed : Boolean,
    @SerializedName("live_stream_link")
    val liveStreamLink : String?
    ) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name+prettyName)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getDescription(): String {
        return "Championship"
    }

    override fun getImgRes(): String {
        return backgroundUrl
    }

    override fun onClick(view: View) {
        val activity : Activity = view.context as Activity
        activity.apply {
            val intent = Intent(this, ChampionshipActivity::class.java).apply {
                putExtra("championship_id", id)
            }
            startActivity(intent)
        }
    }
}