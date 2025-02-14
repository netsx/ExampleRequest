package com.example.examenkodeit.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenkodeit.R


class AdapterListEpisodes (private val characterList: List<String>,):
    RecyclerView.Adapter<AdapterListEpisodes.EpisodeViewHolder>(){


    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEpisodes: TextView = itemView.findViewById(R.id.tvEpisode)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_espisodes, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun getItemCount(): Int {
       return characterList.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val character = characterList[position]
        holder.tvEpisodes.text = character
    }


}