package com.example.motorsportspotter.components.recyclerviews.entities

class Event(
    val eventName: String,
    val date: String,
    val trackName: String,
    val imageRes: String,
    val championshipName: String
) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(eventName)
    }

    override fun getTitle(): String {
        return eventName
    }

    override fun getDescription(): String {
        return date
    }

    override fun getImgRes(): String {
        return imageRes
    }
}