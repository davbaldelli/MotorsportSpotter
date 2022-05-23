package com.example.motorsportspotter.components.recyclerviews.entities

class Championship(val name: String, val year : Int) : Searchable {
    override fun matchSearchQuery(query: (String) -> Boolean): Boolean {
        return query(name)
    }

}