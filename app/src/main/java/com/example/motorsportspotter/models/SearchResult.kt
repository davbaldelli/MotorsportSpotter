package com.example.motorsportspotter.models

import android.view.View

interface SearchResult {
    fun getTitle() : String
    fun getSubtitle() : String
    fun getImgRes() : String
    fun onClick(view: View)
}