package com.example.motorsportspotter.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.motorsportspotter.databinding.*
import com.example.motorsportspotter.models.Event

class EventCardAdapter<T : EventCardViewHolderInt>(private val viewHolderCreator : (Context, Int) -> T, private val itemType : (Int) -> Int) : ListAdapter<Event, T>(EventCardAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderCreator(parent.context, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return itemType(position)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }
}

abstract class EventCardViewHolderInt(binding: ViewDataBinding) : ViewHolder(binding.root){
    abstract fun bind(event: Event)
}

class ChampEventCardViewHolder(private var binding: ChampionshipEventCardBinding): EventCardViewHolderInt(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class TrackEventViewHolder(private var binding: TrackEventCardBinding): EventCardViewHolderInt(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class SimilarEventCardViewHolder(private var binding: EventCardSimilarBinding): EventCardViewHolderInt(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class FirstEventCardViewHolder(private var binding: EventCardVerticalBinding): EventCardViewHolderInt(binding) {
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class EventCardViewHolder(private var binding: EventCardBinding): EventCardViewHolderInt(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}