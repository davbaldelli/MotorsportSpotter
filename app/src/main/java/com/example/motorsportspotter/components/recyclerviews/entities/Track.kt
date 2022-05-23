package com.example.motorsportspotter.components.recyclerviews.entities

class Track(val name : String, val coordinates : String) : Searchable {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name)
    }
}