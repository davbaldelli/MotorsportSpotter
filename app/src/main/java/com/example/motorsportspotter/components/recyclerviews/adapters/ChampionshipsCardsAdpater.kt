package com.example.motorsportspotter.components.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.motorsportspotter.components.recyclerviews.entities.Championship
import com.example.motorsportspotter.databinding.ChampionshipCardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChampionshipsCardsAdapter(val fragment: Fragment):  ListAdapter<Championship, ChampionshipViewHolder>(ChampionshipsCardsAdapter) {
    companion object DiffCallback : DiffUtil.ItemCallback<Championship>() {
        override fun areItemsTheSame(oldItem: Championship, newItem: Championship): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Championship, newItem: Championship): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipViewHolder {
        return ChampionshipViewHolderImpl(ChampionshipCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ChampionshipViewHolder, position: Int) {
        val championship = getItem(position)
        holder.bind(championship)
    }

    override fun submitList(list: List<Championship>?) {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                super.submitList(list)
            }
        }
    }

}

abstract class ChampionshipViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(championship: Championship)
}


class ChampionshipViewHolderImpl(private var binding: ChampionshipCardBinding): ChampionshipViewHolder(binding){
    override fun bind(championship: Championship) {
        binding.championship = championship
        binding.executePendingBindings()
    }
}