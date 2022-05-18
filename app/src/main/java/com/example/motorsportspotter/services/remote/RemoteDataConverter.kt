package com.example.motorsportspotter.services.remote

import retrofit2.Converter
import java.util.stream.Collectors

class RemoteDataConverter<F,T>(private val converter: Converter<F, T>) {
    fun convertAllToEntities(items : List<F>) : List<T?>{
        return items.stream().map { item: F -> converter.convert(item) }.collect(Collectors.toList())
    }
}