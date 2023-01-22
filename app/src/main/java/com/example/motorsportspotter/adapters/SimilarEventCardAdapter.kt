package com.example.motorsportspotter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.databinding.EventCardSimilarBinding
import com.example.motorsportspotter.databinding.EventCardVerticalBinding
import com.example.motorsportspotter.models.Event

class VerticalEventCardAdapter : ListAdapter<Event, SimilarEventCardViewHolder>(EventCardAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarEventCardViewHolder {
        return VerticalEventCardViewHolderImpl(EventCardSimilarBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: SimilarEventCardViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

}

abstract class SimilarEventCardViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(event: Event)
}


class VerticalEventCardViewHolderImpl(private var binding: EventCardSimilarBinding): SimilarEventCardViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}