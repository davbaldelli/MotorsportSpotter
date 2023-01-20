package com.example.motorsportspotter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.models.Track
import com.example.motorsportspotter.databinding.TrackCardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TrackCardsAdapter(val fragment: Fragment) : ListAdapter<Track, TrackViewHolder>(
    TrackCardsAdapter
) {
    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return  TracViewHolderImpl(TrackCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<Track>?) {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                super.submitList(list)
            }
        }
    }
}

abstract class TrackViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(track: Track)
}

class TracViewHolderImpl(private var binding: TrackCardBinding): TrackViewHolder(binding){
    override fun bind(track: Track) {
        binding.track = track
        binding.executePendingBindings()
    }
}