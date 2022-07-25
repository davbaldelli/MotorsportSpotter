package com.example.motorsportspotter.components.recyclerviews.entities

class Championship(val name: String, val year : Int, val prettyName : String, val backgroundUrl : String, val logoUrl : String) : Searchable, SearchResult {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name+prettyName)
    }

    override fun getTitle(): String {
        return prettyName
    }

    override fun getDescription(): String {
        return "Championship"
    }

    override fun getImgRes(): String {
        return logoUrl
    }
}