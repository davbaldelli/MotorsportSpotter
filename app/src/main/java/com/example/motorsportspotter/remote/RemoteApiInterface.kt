package com.example.motorsportspotter.remote


import com.example.motorsportspotter.database.entities.*
import retrofit2.Call
import retrofit2.http.GET

interface RemoteApiInterface {
    @GET("tracks")
    fun allTracks(): Call<List<RemoteTrack>>?

    @GET("championships")
    fun allChampionships() : Call<List<RemoteChampionship>>?

    @GET("events")
    fun allEvents() : Call<List<RemoteEvent>>?

    @GET("sessions")
    fun allSessions() : Call<List<Session>>?

}