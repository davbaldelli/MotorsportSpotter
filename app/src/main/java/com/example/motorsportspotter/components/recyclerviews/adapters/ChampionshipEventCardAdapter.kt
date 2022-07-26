package com.example.motorsportspotter.components.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.databinding.ChampionshipEventCardBinding

class ChampionshipEventCardAdapter : ListAdapter<Event, ChampEventCardViewHolder>(EventCardAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventName == newItem.eventName
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventName == newItem.eventName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampEventCardViewHolder {
        return ChampEventCardViewHolderImpl(ChampionshipEventCardBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ChampEventCardViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

}

abstract class ChampEventCardViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(event: Event)
}


class ChampEventCardViewHolderImpl(private var binding: ChampionshipEventCardBinding): ChampEventCardViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}