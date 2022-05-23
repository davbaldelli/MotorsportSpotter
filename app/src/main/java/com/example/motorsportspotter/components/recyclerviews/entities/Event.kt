package com.example.motorsportspotter.components.recyclerviews.entities

class Event(
    val eventName: String,
    val date: String,
    val trackName: String,
    val imageRes: String,
    val championshipName: String
) : Searchable {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(eventName)
    }
}