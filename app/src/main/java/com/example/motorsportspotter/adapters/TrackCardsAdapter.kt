package com.example.motorsportspotter.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.models.Track
import com.example.motorsportspotter.databinding.TrackCardBinding
import com.example.motorsportspotter.databinding.TrackCardHorizontalBinding


class TrackCardsAdapter<T : TrackViewHolderInt>(private val viewHolderCreator : (Context) -> T) : ListAdapter<Track, T>(TrackCardsAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderCreator(parent.context)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(getItem(position))
    }
}

abstract class TrackViewHolderInt(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(track: Track)
}

class TrackViewHolder(private var binding: TrackCardBinding): TrackViewHolderInt(binding){
    override fun bind(track: Track) {
        binding.track = track
        binding.executePendingBindings()
    }
}

class HorizontalTrackViewHolder(private var binding: TrackCardHorizontalBinding): TrackViewHolderInt(binding){
    override fun bind(track: Track) {
        binding.track = track
        binding.executePendingBindings()
    }
}