package com.example.motorsportspotter.components.recyclerviews.entities

class Event(
    val eventName: String,
    val startDate: String,
    val endDate: String,
    val trackName: String?,
    val trackId : Int?,
    val imageRes: String,
    val championshipName: String?,
    val championshipId : Int?,
    val trackLocation : Pair<Double, Double>?
) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(eventName)
    }

    override fun getTitle(): String {
        return eventName
    }

    override fun getDescription(): String {
        return endDate
    }

    override fun getImgRes(): String {
        return imageRes
    }
}