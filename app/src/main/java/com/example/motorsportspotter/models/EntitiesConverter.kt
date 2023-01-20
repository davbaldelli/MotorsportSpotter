package com.example.motorsportspotter.models

import java.util.stream.Collectors

class EntitiesConverter<F,T>(private val converter: (F) -> T) {
    fun convertAll(items : List<F>) : List<T>{
        return items.stream().map { item: F -> converter(item) }.collect(Collectors.toList())
    }
}

