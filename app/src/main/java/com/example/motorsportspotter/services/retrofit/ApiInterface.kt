package com.example.motorsportspotter.services.retrofit

import com.example.motorsportspotter.components.recyclerviews.entities.*
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

    @GET("news")
    fun allNews() : Call<List<News>>?
}