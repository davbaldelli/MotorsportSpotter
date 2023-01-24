package com.example.motorsportspotter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.models.SearchResult
import com.example.motorsportspotter.databinding.EventSearchResultCardBinding

class SearchResultAdapter : ListAdapter<SearchResult, SearchResultViewHolder>(
    SearchResultAdapter
){

    companion object DiffCallback : DiffUtil.ItemCallback<SearchResult>() {
        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.getTitle() == newItem.getTitle()
        }

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.getTitle() == newItem.getTitle()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(EventSearchResultCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

}

class SearchResultViewHolder(private var binding : EventSearchResultCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result : SearchResult){
        binding.searchResult = result
        binding.executePendingBindings()
    }
}
