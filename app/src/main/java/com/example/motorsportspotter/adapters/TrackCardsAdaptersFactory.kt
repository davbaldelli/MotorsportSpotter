package com.example.motorsportspotter.adapters

import android.view.LayoutInflater
import com.example.motorsportspotter.databinding.TrackCardBinding
import com.example.motorsportspotter.databinding.TrackCardHorizontalBinding

class TrackCardsAdaptersFactory {
    companion object {
        fun getTrackCardAdapter() : TrackCardsAdapter<TrackViewHolder> {
            return TrackCardsAdapter {context ->
                TrackViewHolder(TrackCardBinding.inflate(LayoutInflater.from(context)))
            }
        }
        fun getHorizontalTrackCardAdapter() : TrackCardsAdapter<HorizontalTrackViewHolder> {
            return TrackCardsAdapter {context ->
                HorizontalTrackViewHolder(TrackCardHorizontalBinding.inflate(LayoutInflater.from(context)))
            }
        }
    }
}