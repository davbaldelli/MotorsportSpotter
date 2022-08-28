package com.example.motorsportspotter.components.recyclerviews.entities

import java.time.LocalDateTime

data class Session (
    val id : Int?,
    val name : String,
    val startDateTime : LocalDateTime,
    val durationMin : Int?,
    val durationLaps : Int?
    )