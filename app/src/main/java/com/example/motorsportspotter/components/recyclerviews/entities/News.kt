package com.example.motorsportspotter.components.recyclerviews.entities

import java.time.LocalDate

class News(
    val id : Int,
    val title : String,
    val subtitle : String,
    val content : String,
    val author : String,
    val date : LocalDate,
    val image : String
)