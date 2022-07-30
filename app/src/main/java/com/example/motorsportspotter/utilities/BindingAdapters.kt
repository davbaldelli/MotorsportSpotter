package com.example.motorsportspotter.utilities

import android.location.Address
import android.location.Geocoder
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.motorsportspotter.components.recyclerviews.adapters.ChampionshipEventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.EventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.adapters.VerticalEventCardAdapter
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.room.entities.DBEntitiesConvertersFactory as Converters
import com.example.motorsportspotter.room.entities.EventWithTrackAndChamp
import com.example.motorsportspotter.room.viewmodel.ChampionshipsViewModel
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
fun bindAddress(textView: TextView, locationName : String?){
    locationName?.let {
        val geocoder = Geocoder(textView.context, Locale.ITALY)
        val addresses: List<Address> = geocoder.getFromLocationName(it, 1)
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

@BindingAdapter("similarDataList")
fun bindSimilarRecyclerView(recyclerView: RecyclerView, data: List<Event>?){
    data?.let {
        val adapter = recyclerView.adapter as VerticalEventCardAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("championshipDataList")
fun bindChampionshipRecyclerView(recyclerView: RecyclerView, events : List<Event>?){
    events?.let {
        val adapter = recyclerView.adapter as ChampionshipEventCardAdapter
        adapter.submitList(events)
    }
}


