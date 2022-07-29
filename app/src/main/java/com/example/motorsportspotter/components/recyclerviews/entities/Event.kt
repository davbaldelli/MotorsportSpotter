package com.example.motorsportspotter.components.recyclerviews.entities

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
    val championshipIconUrl : String?,
    val trackLocation : Pair<String, String>?
) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(eventName)
    }

    override fun getTitle(): String {
        return eventName
    }

    override fun getDescription(): String {
        return endDate.toString()
    }

    override fun getImgRes(): String {
        return imageUrl
    }

    fun getPeriod() : String{
        val formatter = DateTimeFormatter.ofPattern("MMM")
        return "${startDate.dayOfMonth}${if (startDate.monthValue != endDate.monthValue) " "+startDate.format(formatter)+" " else ""}-${endDate.dayOfMonth} ${endDate.format(formatter)}"
    }
}