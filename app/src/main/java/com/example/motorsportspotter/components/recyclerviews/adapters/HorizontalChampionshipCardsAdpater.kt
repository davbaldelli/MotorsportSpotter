package com.example.motorsportspotter.components.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.Championship
import com.example.motorsportspotter.databinding.HorizontalChampionshipCardBinding

class HorizontalChampionshipCardsAdapter:  ListAdapter<Championship, HorizontalChampionshipViewHolder>(HorizontalChampionshipCardsAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Championship>() {
        override fun areItemsTheSame(oldItem: Championship, newItem: Championship): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Championship, newItem: Championship): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalChampionshipViewHolder {
        return HorizontalChampionshipViewHolderImpl(HorizontalChampionshipCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HorizontalChampionshipViewHolder, position: Int) {
        val championship = getItem(position)
        holder.bind(championship)
    }

}

abstract class HorizontalChampionshipViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(championship: Championship)
}


class HorizontalChampionshipViewHolderImpl(private var binding: HorizontalChampionshipCardBinding): HorizontalChampionshipViewHolder(binding){
    override fun bind(championship: Championship) {
        binding.championship = championship
        binding.executePendingBindings()
    }
}