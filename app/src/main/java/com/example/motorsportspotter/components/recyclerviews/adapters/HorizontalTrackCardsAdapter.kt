package com.example.motorsportspotter.components.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.Track
import com.example.motorsportspotter.databinding.HorizontalTrackCardBinding


class HorizontalTrackCardsAdapter : ListAdapter<Track, HorizontalTrackViewHolder>(HorizontalTrackCardsAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalTrackViewHolder {
        return  HorizontalTrackViewHolderImpl(HorizontalTrackCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HorizontalTrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

abstract class HorizontalTrackViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(track: Track)
}

class HorizontalTrackViewHolderImpl(private var binding: HorizontalTrackCardBinding): HorizontalTrackViewHolder(binding){
    override fun bind(track: Track) {
        binding.track = track
        binding.executePendingBindings()
    }
}