package com.example.motorsportspotter.adapters

import android.view.LayoutInflater
import com.example.motorsportspotter.databinding.ChampionshipCardBinding
import com.example.motorsportspotter.databinding.ChampionshipCardHorizontalBinding

class ChampionshipCardAdaptersFactory {
    companion object {
        fun getChampionshipCardAdapter() : ChampionshipsCardsAdapter<ChampionshipViewHolder> {
            return ChampionshipsCardsAdapter { context ->
                ChampionshipViewHolder(ChampionshipCardBinding.inflate(LayoutInflater.from(context)))
            }
        }
        fun getHorizontalChampionshipCardAdapter() : ChampionshipsCardsAdapter<HorizontalChampionshipViewHolder> {
            return ChampionshipsCardsAdapter { context ->
                HorizontalChampionshipViewHolder(ChampionshipCardHorizontalBinding.inflate(
                    LayoutInflater.from(context)))
            }
        }
    }
}