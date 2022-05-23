package com.example.motorsportspotter.components.recyclerviews

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.entities.Event

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

    fun updateList(events : List<Event>){
        cardItemList.addAll(0,events)
        notifyItemRangeInserted(0, events.count())
    }
}

class CardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val placeEventImage : ImageView = itemView.findViewById(R.id.place_imageview)
    val placeEventTitle : TextView = itemView.findViewById(R.id.place_event_title)
    val placeEventDate : TextView = itemView.findViewById(R.id.place_event_date)
}

