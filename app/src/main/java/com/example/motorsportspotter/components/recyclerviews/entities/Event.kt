package com.example.motorsportspotter.components.recyclerviews.entities

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.motorsportspotter.activities.EventActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Event(
    val eventId : Int,
    val eventName: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val trackName: String?,
    val trackId : Int?,
    val imageUrl: String,
    val championshipName: String?,
    val championshipId : Int?,
    val championshipFollowed : Boolean?,
    val championshipIconUrl : String?,
    val trackLocation : Pair<String, String>?,
    val trackLocationName : String?
) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(eventName)
    }

    override fun getTitle(): String {
        return "$championshipName • $eventName"
    }

    override fun getDescription(): String {
        return getPeriod()
    }

    override fun getImgRes(): String {
        return imageUrl
    }

    override fun onClick(view: View) {
        val activity : Activity = view.context as Activity
        activity.apply {
            val intent = Intent(this, EventActivity::class.java).apply {
                putExtra("event_id", eventId)
            }
            startActivity(intent)
        }
    }

    fun getPeriod() : String{
        val formatter = DateTimeFormatter.ofPattern("MMM")
        return "${startDate.dayOfMonth}${if (startDate.monthValue != endDate.monthValue) " "+startDate.format(formatter)+" " else ""}-${endDate.dayOfMonth} ${endDate.format(formatter)}"
    }
}