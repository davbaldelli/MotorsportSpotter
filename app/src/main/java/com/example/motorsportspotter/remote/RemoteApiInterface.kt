package com.example.motorsportspotter.remote


import com.example.motorsportspotter.room.entities.*
import retrofit2.Call
import retrofit2.http.GET

interface RemoteApiInterface {
    @GET("tracks")
    fun allTracks(): Call<List<Track>>?

    @GET("championships")
    fun allChampionships() : Call<List<Championship>>?

    @GET("events")
    fun allEvents() : Call<List<Event>>?

    @GET("sessions")
    fun allSessions() : Call<List<Session>>?

}