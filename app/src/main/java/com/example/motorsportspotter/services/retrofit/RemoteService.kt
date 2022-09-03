package com.example.motorsportspotter.services.retrofit


import com.example.motorsportspotter.room.entities.*
import retrofit2.Call
import retrofit2.http.GET

interface RemoteService {
    @GET("tracks")
    fun allTracks(): Call<List<Track>>?

    @GET("championships")
    fun allChampionships() : Call<List<Championship>>?

    @GET("events")
    fun allEvents() : Call<List<Event>>?

    @GET("sessions")
    fun allSessions() : Call<List<Session>>?

    @GET("news")
    fun allNews() : Call<List<News>>?
}