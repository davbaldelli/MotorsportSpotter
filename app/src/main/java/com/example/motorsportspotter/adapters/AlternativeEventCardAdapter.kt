package com.example.motorsportspotter.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.motorsportspotter.databinding.ChampionshipEventCardBinding
import com.example.motorsportspotter.databinding.EventCardSimilarBinding
import com.example.motorsportspotter.databinding.TrackEventCardBinding
import com.example.motorsportspotter.models.Event

class AlternativeEventCardAdapter<T : AltEventCardViewHolder>(private val viewHolderCreator : (Context) -> T) : ListAdapter<Event, T>(AlternativeEventCardAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderCreator(parent.context)
    }


    override fun onBindViewHolder(holder: T, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }
}

abstract class AltEventCardViewHolder(binding: ViewDataBinding) : ViewHolder(binding.root){
    abstract fun bind(event: Event)
}

class ChampEventCardViewHolder(private var binding: ChampionshipEventCardBinding): AltEventCardViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class TrackEventViewHolder(private var binding: TrackEventCardBinding): AltEventCardViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class SimilarEventCardViewHolder(private var binding: EventCardSimilarBinding): AltEventCardViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}