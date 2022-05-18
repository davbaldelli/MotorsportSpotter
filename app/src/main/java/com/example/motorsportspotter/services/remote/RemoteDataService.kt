package com.example.motorsportspotter.services.remote

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface RemoteDataService {

    @GET("tracks/all")
    fun getAllTracks() : Call<List<Track>>

    @GET("championships/all")
    fun getAllChampionships() : Call<List<Championship>>

    @GET("event/all")
    fun getAllEvents() : Call<List<Event>>
}

data class Track(
    val id : Int,
    val name : String,
    val coordinates : String,
)

data class Championship(
    val id: Int,
    val name : String,
    val year: Int,
)

data class Event(
    val id: Int,
    val trackId: Int,
    val championshipId : Int,
    val name : String,
    val date: Date
)