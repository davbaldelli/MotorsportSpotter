package com.example.motorsportspotter.components.recyclerviews.entities

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Session (
    val id : Int?,
    val name : String,
    @SerializedName("start_date_time")
    val startDateTime : LocalDateTime,
    @SerializedName("duration_min")
    val durationMin : Int?,
    @SerializedName("duration_laps")
    val durationLaps : Int?
    )