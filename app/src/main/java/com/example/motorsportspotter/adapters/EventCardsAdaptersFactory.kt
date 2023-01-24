package com.example.motorsportspotter.adapters

import android.view.LayoutInflater
import com.example.motorsportspotter.databinding.ChampionshipEventCardBinding
import com.example.motorsportspotter.databinding.EventCardSimilarBinding
import com.example.motorsportspotter.databinding.TrackEventCardBinding

class EventCardsAdaptersFactory {
    companion object {
        fun getChampionshipEventCardAdapter() : AlternativeEventCardAdapter<ChampEventCardViewHolder>{
            return AlternativeEventCardAdapter { context ->
                ChampEventCardViewHolder(ChampionshipEventCardBinding.inflate(LayoutInflater.from(context)))
            }
        }

        fun getTrackEventCardAdapter() : AlternativeEventCardAdapter<TrackEventViewHolder>{
            return AlternativeEventCardAdapter {context ->
                TrackEventViewHolder(TrackEventCardBinding.inflate(LayoutInflater.from(context)))
            }
        }

        fun getSimilarEventCardAdapter() : AlternativeEventCardAdapter<SimilarEventCardViewHolder>{
            return AlternativeEventCardAdapter {context ->
                SimilarEventCardViewHolder(EventCardSimilarBinding.inflate(LayoutInflater.from(context)))
            }
        }

    }
}