package com.example.motorsportspotter.components.recyclerviews.entities

interface Searchable {
    fun matchSearchQuery(query : (String) -> Boolean) : Boolean
}