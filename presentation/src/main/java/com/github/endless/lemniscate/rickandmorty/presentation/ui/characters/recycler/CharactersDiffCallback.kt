package com.github.endless.lemniscate.rickandmorty.presentation.ui.characters.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.endless.lemniscate.rickandmorty.domain.models.Character

class CharactersDiffCallback(private val oldList: List<Character>,
                             private val newList: List<Character>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.name == newItem.name &&
                oldItem.species == newItem.species &&
                oldItem.status == newItem.status &&
                oldItem.gender == newItem.gender &&
                oldItem.imageUrl == newItem.imageUrl
    }
}