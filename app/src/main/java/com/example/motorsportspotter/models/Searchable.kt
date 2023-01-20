package com.example.motorsportspotter.models

interface Searchable {
    fun matchSearchQuery(query : (String) -> Boolean) : Boolean
}