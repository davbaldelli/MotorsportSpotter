package com.example.motorsportspotter.components.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.databinding.TrackEventCardBinding

class TrackEventsAdapter:  ListAdapter<Event, TrackEventViewHolder>(TrackEventsAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackEventViewHolder {
        return TrackEventViewHolderImpl(TrackEventCardBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun submitList(list: List<Event>?) {
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: TrackEventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

}

abstract class TrackEventViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(event: Event)
}


class TrackEventViewHolderImpl(private var binding: TrackEventCardBinding): TrackEventViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}