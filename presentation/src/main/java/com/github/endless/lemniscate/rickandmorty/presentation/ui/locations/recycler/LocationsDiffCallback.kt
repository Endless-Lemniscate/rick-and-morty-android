package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.endless.lemniscate.rickandmorty.domain.models.Location

class LocationsDiffCallback(private val oldList: List<Location>,
                            private val newList: List<Location>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.name == newItem.name &&
                oldItem.dimension == newItem.dimension &&
                oldItem.type == newItem.type
    }
}