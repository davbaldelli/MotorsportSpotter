package com.example.motorsportspotter.models

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.motorsportspotter.activities.ChampionshipActivity
import com.google.gson.annotations.SerializedName

class Championship(
    val id: Int,
    val name: String,
    val year: Int,
    val description: String,
    val prettyName: String,
    val backgroundUrl: String,
    val logoUrl: String,
    val followed: Boolean,
    val liveStreamLink: String?,

) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name + prettyName)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSubtitle(): String {
        return "Championship"
    }

    override fun getImgRes(): String {
        return logoUrl
    }

    override fun onClick(view: View) {
        val activity: Activity = view.context as Activity
        activity.apply {
            val intent = Intent(this, ChampionshipActivity::class.java).apply {
                putExtra("championship_id", id)
            }
            startActivity(intent)
        }
    }
}