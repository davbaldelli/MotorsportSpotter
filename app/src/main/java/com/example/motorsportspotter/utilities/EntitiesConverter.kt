package com.example.motorsportspotter.utilities

interface EntitiesConverter<A,B> {
    fun convert(item : A) : B
}