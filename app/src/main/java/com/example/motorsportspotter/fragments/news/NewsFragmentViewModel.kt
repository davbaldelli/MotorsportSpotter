package com.example.motorsportspotter.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsFragmentViewModel : ViewModel() {

    var newsId = MutableLiveData<Int>()

    fun setNewsId(id : Int){
        newsId.value = id
    }

}