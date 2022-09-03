package com.example.motorsportspotter.components.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.News
import com.example.motorsportspotter.databinding.NewsCardBinding

class NewsCardAdapter() : ListAdapter<News, NewsCardViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.content == newItem.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCardViewHolder {
        return NewsCardViewHolderImpl(NewsCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

abstract class NewsCardViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(news: News)
}

class NewsCardViewHolderImpl(private var binding : NewsCardBinding): NewsCardViewHolder(binding){
    override fun bind(news: News) {
        binding.news = news
        binding.executePendingBindings()
    }
}