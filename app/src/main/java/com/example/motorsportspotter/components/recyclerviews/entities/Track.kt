package com.example.motorsportspotter.components.recyclerviews.entities

class Track(
    val name : String,
    val coordinates : String,
    val backgroundUrl : String,
    val logoUrl : String
    ) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getDescription(): String {
        return "Circuit"
    }

    override fun getImgRes(): String {
        return ""
    }
}