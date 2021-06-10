package com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.endless.lemniscate.rickandmorty.domain.models.Episode

class EpisodesDiffCallback(private val oldList: List<Episode>,
                           private val newList: List<Episode>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.name == newItem.name &&
                oldItem.air_date == newItem.air_date &&
                oldItem.episode == newItem.episode
    }
}