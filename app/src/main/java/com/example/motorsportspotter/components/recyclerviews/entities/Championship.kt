package com.example.motorsportspotter.components.recyclerviews.entities

class Championship(val name: String, val year : Int) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getDescription(): String {
        return "Championship"
    }

    override fun getImgRes(): String {
        return ""
    }
}