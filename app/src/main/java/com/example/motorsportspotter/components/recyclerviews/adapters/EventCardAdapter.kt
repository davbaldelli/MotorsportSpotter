package com.example.motorsportspotter.components.recyclerviews.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.Event
import com.example.motorsportspotter.databinding.EventCardBinding
import com.example.motorsportspotter.databinding.FirstEventCardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class EventCardAdapter(val fragment: Fragment) : ListAdapter<Event, EventCardViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventName == newItem.eventName
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventName == newItem.eventName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventCardViewHolder {
        return if (viewType == 0) FirstCardViewHolder(FirstEventCardBinding.inflate(LayoutInflater.from(parent.context)))
        else NormalCardViewHolder(EventCardBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)  0 else 1
    }

    override fun onBindViewHolder(holder: EventCardViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    override fun submitList(list: List<Event>?) {
        super.submitList(list)
    }
}

abstract class EventCardViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(event: Event)
}

class FirstCardViewHolder(private var binding: FirstEventCardBinding): EventCardViewHolder(binding) {
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

class NormalCardViewHolder(private var binding: EventCardBinding): EventCardViewHolder(binding){
    override fun bind(event: Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}


