package com.example.motorsportspotter.adapters

import android.content.Context
import android.view.LayoutInflater
import com.example.motorsportspotter.databinding.*

class EventCardsAdaptersFactory {
    companion object {

        fun getEventCardAdapter(): EventCardAdapter<EventCardViewHolderInt>{
            return EventCardAdapter ({
                    context, viewType ->
                    if (viewType == 0) FirstEventCardViewHolder(EventCardVerticalBinding.inflate(LayoutInflater.from(context)))
                    else EventCardViewHolder(EventCardBinding.inflate(LayoutInflater.from(context)))
                }, {
                    position -> if (position == 0)  0 else 1
                })
        }
        fun getChampionshipEventCardAdapter() : EventCardAdapter<ChampEventCardViewHolder>{
            return EventCardAdapter ({ context, _ ->
                ChampEventCardViewHolder(ChampionshipEventCardBinding.inflate(LayoutInflater.from(context)))
            }, {0})
        }

        fun getTrackEventCardAdapter() : EventCardAdapter<TrackEventViewHolder>{
            return EventCardAdapter ({ context, _ ->
                TrackEventViewHolder(TrackEventCardBinding.inflate(LayoutInflater.from(context)))
            }, {0})
        }

        fun getSimilarEventCardAdapter() : EventCardAdapter<SimilarEventCardViewHolder>{
            return EventCardAdapter ({ context, _ ->
                SimilarEventCardViewHolder(EventCardSimilarBinding.inflate(LayoutInflater.from(context)))
            }, {0})
        }

    }
}