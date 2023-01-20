package com.example.motorsportspotter.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class Session (
    val id : Int?,
    val name : String,
    val date : String,
    val time : String,
    val timezone : String,
    @SerializedName("durationMin")
    val durationMin : Int?,
    @SerializedName("durationLaps")
    val durationLaps : Int?
    ) {
    fun startDateTime() : LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        val zonedDateTime = LocalDateTime.parse(this.date+" "+this.time, formatter).atZone(ZoneId.of(this.timezone))
        return zonedDateTime.withZoneSameInstant(TimeZone.getDefault().toZoneId()).toLocalDateTime()
    }

}