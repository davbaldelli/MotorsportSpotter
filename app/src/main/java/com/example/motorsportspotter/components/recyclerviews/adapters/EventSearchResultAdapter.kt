package com.example.motorsportspotter.components.recyclerviews.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.R
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.components.recyclerviews.entities.SearchResult
import com.example.motorsportspotter.databinding.EventSearchResultCardBinding

class EventSearchResultAdapter : ListAdapter<SearchResult, EventSearchResultViewHolder>(EventSearchResultAdapter){

    companion object DiffCallback : DiffUtil.ItemCallback<SearchResult>() {
        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.getTitle() == newItem.getTitle()
        }

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.getTitle() == newItem.getTitle()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventSearchResultViewHolder {
        return EventSearchResultViewHolder(EventSearchResultCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: EventSearchResultViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

}

class EventSearchResultViewHolder(private var binding : EventSearchResultCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result : SearchResult){
        binding.searchResult = result
        binding.executePendingBindings()
    }
}
