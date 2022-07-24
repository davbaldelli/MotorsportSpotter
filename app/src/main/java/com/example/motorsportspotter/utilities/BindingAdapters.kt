package com.example.motorsportspotter.utilities

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<EventWithTrackAndChamp>) {
    val adapter = recyclerView.adapter as EventCardAdapter
    val eventConverter = DBEntitiesConvertersFactory.getEventsConverter()
    adapter.submitList(eventConverter.convertAll(data))
}


