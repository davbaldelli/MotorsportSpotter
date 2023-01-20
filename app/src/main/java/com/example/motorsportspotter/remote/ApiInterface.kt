package com.example.motorsportspotter.services.remote

import com.example.motorsportspotter.models.*
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("tracks")
    fun allTracks(): Call<List<Track>>?

    @GET("championships")
    fun allChampionships() : Call<List<Championship>>?

    @GET("events")
    fun allEvents() : Call<List<Event>>

    @GET("sessions")
    fun allSessions() : Call<List<Session>>?

}