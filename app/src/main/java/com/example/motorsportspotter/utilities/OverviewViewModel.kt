package com.example.motorsportspotter.utilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val _photos = MutableLiveData<Photo>()
    val photos: LiveData<Photo> = _photos


    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                val listResult = PhotosApi.retrofitService.getPhotos()
                _photos.value = PhotosApi.retrofitService.getPhotos()[0]

                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}

