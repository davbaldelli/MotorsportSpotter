package com.example.motorsportspotter.components.recyclerviews.entities

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.motorsportspotter.activities.EventActivity
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Event(
    val id : Int,
    val name: String,
    @SerializedName("startDate")
    val startDate: LocalDate,
    @SerializedName("endDate")
    val endDate: LocalDate,
    @SerializedName("image")
    val imageUrl: String,
    val track : Track,
    val championship: Championship,
    val sessions : List<Session>
) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name)
    }

    override fun getTitle(): String {
        return "${championship.prettyName} • $name"
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
                putExtra("event_id", id)
            }
            startActivity(intent)
        }
    }

    fun getPeriod() : String{
        val formatter = DateTimeFormatter.ofPattern("MMM")
        return "${startDate.dayOfMonth}${if (startDate.monthValue != endDate.monthValue) " "+startDate.format(formatter)+" " else ""}-${endDate.dayOfMonth} ${endDate.format(formatter)}"
    }

    fun getSessionsDesc() : String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm");
        return sessions.sortedBy { session -> session.startDateTime() }.fold("") { res, session ->
            res + "${session.name}: ${session.startDateTime().dayOfWeek.name.lowercase()}, " +
                    "${session.startDateTime().month.name.lowercase()} ${session.startDateTime().dayOfMonth}, " +
                    "${session.startDateTime().format(formatter)}\n"
        }
    }
}