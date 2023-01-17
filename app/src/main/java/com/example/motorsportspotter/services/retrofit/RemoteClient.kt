package com.example.motorsportspotter.services.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteClient {
    companion object{
        fun getRemoteService() : RemoteService {

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm")
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://spotter.davidebaldelli.it:7151/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(RemoteService::class.java)
        }
    }
}