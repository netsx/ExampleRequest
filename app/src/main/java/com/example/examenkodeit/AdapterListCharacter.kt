package com.example.examenkodeit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenkodeit.model.Result
import com.squareup.picasso.Picasso

class AdapterListCharacter(private val characterList: List<Result>,
                           private val listener: OnItemClickListener ):
    RecyclerView.Adapter<AdapterListCharacter.CharacterViewHolder>(){




    inner class CharacterViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvEpisodes)
        val imageCharacter :ImageView = itemView.findViewById(R.id.imageCharacter)


        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.tvName.text = character.name

        Picasso.get()
            .load(characterList[position].image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageCharacter)

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}
interface OnItemClickListener {
    fun onItemClick(position: Int )
}