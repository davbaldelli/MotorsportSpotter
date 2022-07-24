package com.example.motorsportspotter.components.recyclerviews.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.databinding.FirstEventCardBinding

class EventCardAdapter : ListAdapter<Event, CardViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventName == newItem.eventName
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventName == newItem.eventName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(FirstEventCardBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun getItemViewType(position: Int): Int {
        //return if (position == 0)  0 else 1
        return 0
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

}

class CardViewHolder(private var binding: FirstEventCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}


