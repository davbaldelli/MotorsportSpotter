package com.example.motorsportspotter.models

import android.view.View

interface SearchResult {
    fun getTitle() : String
    fun getDescription() : String
    fun getImgRes() : String
    fun onClick(view: View)
}