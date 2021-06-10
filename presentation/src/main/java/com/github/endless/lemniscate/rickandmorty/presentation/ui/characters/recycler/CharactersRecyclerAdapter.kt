package com.github.endless.lemniscate.rickandmorty.presentation.ui.characters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.endless.lemniscate.rickandmorty.R
import com.github.endless.lemniscate.rickandmorty.databinding.CharacterItemLayoutBinding
import com.github.endless.lemniscate.rickandmorty.databinding.LocationItemLayoutBinding
import com.github.endless.lemniscate.rickandmorty.domain.models.Character
import com.github.endless.lemniscate.rickandmorty.domain.models.Location

class CharactersRecyclerAdapter(private val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<CharactersRecyclerAdapter.ViewHolder>() {

    private val characters = ArrayList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: CharacterItemLayoutBinding,
                           private val context: Context): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            binding.apply {
                name.text = item.name
                species.text = item.species
                status.text = item.status
                gender.text = item.gender
                image.text = item.imageUrl
            }
            binding.root.setOnClickListener {
                itemClickListener.itemClicked(item)
            }
        }
    }

    fun updateList(newList: List<Character>) {
        val diffCallback = CharactersDiffCallback(characters, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        characters.clear()
        characters.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}

interface ItemClickListener {
    fun itemClicked(character: Character)
}
