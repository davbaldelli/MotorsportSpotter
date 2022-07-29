package com.example.motorsportspotter.utilities

import android.location.Address
import android.location.Geocoder
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converters
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp
import java.util.*

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {

        }
    }
}

@BindingAdapter("addressCoordinates")
fun bindAddress(textView: TextView, coordinates: Pair<String, String>?){
    coordinates?.let {
        val geocoder = Geocoder(textView.context, Locale.ITALY)
        val addresses: List<Address> = geocoder.getFromLocation(it.first.toDouble(), it.second.toDouble(), 1)
        val address = addresses[0]
        textView.text = address.getAddressLine(0)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<EventWithTrackAndChamp>?) {
    data?.let{
        val adapter = recyclerView.adapter as EventCardAdapter
        adapter.submitList(Converters.CompleteEventConverter.convertAll(it))
    }
}


