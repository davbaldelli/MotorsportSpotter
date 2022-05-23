package com.example.motorsportspotter.components.recyclerviews.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.entities.Event

class EventSearchResultAdapter(private var itemList: ArrayList<Event>, val activity : Activity) : RecyclerView.Adapter<EventSearchResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventSearchResultViewHolder {
        val layoutView : View = LayoutInflater.from(parent.context).inflate(R.layout.event_search_result_card, parent, false)
        return EventSearchResultViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: EventSearchResultViewHolder, position: Int) {
        val event = itemList[position]
        holder.placeCardTitle.text = event.eventName
        holder.placeCardDesc.text = event.date
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(events : List<Event>){
        itemList.clear()
        itemList.addAll(events)
        notifyDataSetChanged()
    }
}

class EventSearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val placeCardTitle : TextView = itemView.findViewById(R.id.event_search_result_title)
    val placeCardDesc : TextView = itemView.findViewById(R.id.event_search_result_desc)
}
