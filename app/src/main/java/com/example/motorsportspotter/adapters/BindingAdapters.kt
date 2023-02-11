package com.example.motorsportspotter.adapters

import android.R
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.motorsportspotter.adapters.*
import com.example.motorsportspotter.models.Event
import com.example.motorsportspotter.database.entities.Championship
import com.example.motorsportspotter.database.entities.EventWithTrackAndChamp
import com.example.motorsportspotter.database.entities.Track
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.util.*
import com.example.motorsportspotter.database.entities.DBEntitiesConvertersFactory as Converters

val String.flagEmoji: String
    get() {
        val firstLetter = Character.codePointAt(this, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(this, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        CoroutineScope(Dispatchers.IO).launch {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }
}

@BindingAdapter("addressCoordinates")
fun bindAddress(textView: TextView, locationName : String?){
    locationName?.let {
        val geocoder = Geocoder(textView.context, Locale.ITALY)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocationName(it, 1) { addresses ->
                    setAddressLabel(addresses, textView)
                }
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                val addresses = geocoder.getFromLocationName(it, 1)
                addresses?.let {
                    setAddressLabel(addresses, textView)
                }
            }
        } catch (e : IOException){
            textView.text = ""
        }
    }
}

fun setAddressLabel(addresses : List<Address>, textView: TextView) {
    if (addresses.isNotEmpty()) {
        val address = addresses[0]
        textView.text = String.format(
            "%s %s",
            address.getAddressLine(0),
            address.countryCode.flagEmoji
        )
    }
}

@BindingAdapter("scale")
fun bindScale(image : ImageView, string: String){
    image.scaleType = if (string != "Circuit" && string != "Championship") ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.CENTER_INSIDE
}



@BindingAdapter("eventList")
fun bindEventCardList(recyclerView: RecyclerView, data: List<EventWithTrackAndChamp>?) {
    data?.let{
        val adapter = recyclerView.adapter as EventCardAdapter
        adapter.submitList(Converters.EventConverter.convertAll(it))
    }
}


@BindingAdapter("altEventList")
fun bindAlternativeEventCardList(recyclerView: RecyclerView, data: List<Event>?){
    data?.let {
        val adapter = recyclerView.adapter as EventCardAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("championshipsList")
fun bindChampionshipsCardList(recyclerView: RecyclerView, championships : List<Championship>?){
    championships?.let {
        val adapter = recyclerView.adapter as ChampionshipsCardsAdapter
        adapter.submitList(Converters.ChampionshipsConverter.convertAll(it))
    }
}

@BindingAdapter("tracksList")
fun  bindTracksCardList(recyclerView: RecyclerView, tracks : List<Track>?){
    tracks?.let {
        val adapter = recyclerView.adapter as TrackCardsAdapter
        adapter.submitList(Converters.TracksConverter.convertAll(it))
    }
}


@BindingAdapter("optionalView")
fun bindOptionalView(view: View, visible : Boolean){
    if (visible) {
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("optionalRunningIcon")
fun bindRunningIcon(imageView: ImageView, event : Event){
    val current = LocalDate.now()
    if(current >= event.startDate && current <= event.endDate){
        imageView.visibility = View.VISIBLE
        val animFadeOut: Animation = AnimationUtils.loadAnimation(
            imageView.context,
            R.anim.fade_out
        )
        animFadeOut.repeatCount = Animation.INFINITE;
        animFadeOut.repeatMode = Animation.REVERSE;
        imageView.startAnimation(animFadeOut)
    } else {
        imageView.visibility = View.GONE
    }
}

@BindingAdapter("optionalTicketButton")
fun bindOptionalTicketsButton(floatingActionButton: ExtendedFloatingActionButton, event : Event?){
    event?.let {
        if (LocalDate.now() <= event.endDate && LocalDate.now() >= event.startDate && !event.championship.liveStreamLink.isNullOrEmpty()){
            floatingActionButton.visibility = View.VISIBLE
        }
    }

}
