package com.example.motorsportspotter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.models.Championship
import com.example.motorsportspotter.databinding.ChampionshipCardBinding
import com.example.motorsportspotter.databinding.ChampionshipCardHorizontalBinding

class ChampionshipsCardsAdapter<T : ChampionshipViewHolderInt>(private val viewHolderCreator : (Context) -> T):  ListAdapter<Championship, T>(ChampionshipsCardsAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Championship>() {
        override fun areItemsTheSame(oldItem: Championship, newItem: Championship): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Championship, newItem: Championship): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderCreator(parent.context)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val championship = getItem(position)
        holder.bind(championship)
    }

}

abstract class ChampionshipViewHolderInt(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(championship: Championship)
}


class ChampionshipViewHolder(private var binding: ChampionshipCardBinding): ChampionshipViewHolderInt(binding){
    override fun bind(championship: Championship) {
        binding.championship = championship
        binding.executePendingBindings()
    }
}

class HorizontalChampionshipViewHolder(private var binding: ChampionshipCardHorizontalBinding): ChampionshipViewHolderInt(binding){
    override fun bind(championship: Championship) {
        binding.championship = championship
        binding.executePendingBindings()
    }
}