package com.example.motorsportspotter.components.recyclerviews

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.R

class CardAdapter(private var cardItemList : ArrayList<Event>, val activity: Activity) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutView : View = LayoutInflater.from(parent.context).inflate(R.layout.event_card_layout, parent, false)
        return CardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val event = cardItemList[position]
        val imagePath = event.imageRes
        holder.placeEventTitle.text = event.eventName
        holder.placeEventDate.text = event.date
    }

    override fun getItemCount(): Int {
        return cardItemList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(events : List<Event>){
        cardItemList.addAll(events)
        notifyDataSetChanged()
    }
}

data class Event (
    val eventName : String,
    val date : String,
    val trackName : String,
    val imageRes: String,
    val championshipName : String
)

class CardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val placeEventImage : ImageView = itemView.findViewById(R.id.place_imageview)
    val placeEventTitle : TextView = itemView.findViewById(R.id.place_event_title)
    val placeEventDate : TextView = itemView.findViewById(R.id.place_event_date)
}
