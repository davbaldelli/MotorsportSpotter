package com.example.motorsportspotter.components.recyclerviews.entities

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Session (
    val id : Int?,
    val name : String,
    val date : String,
    val time : String,
    @SerializedName("durationMin")
    val durationMin : Int?,
    @SerializedName("durationLaps")
    val durationLaps : Int?
    ) {
    fun startDateTime() : LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(this.date+" "+this.time, formatter)
    }

}