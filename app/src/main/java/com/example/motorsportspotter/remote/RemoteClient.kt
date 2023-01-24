package com.example.motorsportspotter.remote

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class RemoteClient {
    companion object{
        fun getRemoteService() : RemoteApiInterface {

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm")
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://spotter.davidebaldelli.it:7151/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(RemoteApiInterface::class.java)
        }
    }

    internal class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {

        override fun write(out: JsonWriter, value: LocalDate) {
            out.value(DateTimeFormatter.ISO_LOCAL_DATE.format(value))
        }

        override fun read(input: JsonReader): LocalDate = LocalDate.parse(input.nextString())
    }

}