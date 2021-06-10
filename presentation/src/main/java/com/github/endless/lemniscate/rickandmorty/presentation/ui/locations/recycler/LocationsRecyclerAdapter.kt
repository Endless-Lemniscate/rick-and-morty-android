package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.endless.lemniscate.rickandmorty.R
import com.github.endless.lemniscate.rickandmorty.databinding.LocationItemLayoutBinding
import com.github.endless.lemniscate.rickandmorty.domain.models.Location

class LocationsRecyclerAdapter(private val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<LocationsRecyclerAdapter.ViewHolder>() {

    private val locations = ArrayList<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LocationItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location)
    }

    override fun getItemCount(): Int = locations.size

    inner class ViewHolder(private val binding: LocationItemLayoutBinding,
                           private val context: Context): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Location) {
            binding.apply {
                name.text = item.name
                type.text = context.getString(R.string.type_holder, item.type)
                dimension.text = context.getString(R.string.type_holder, item.dimension)
            }
            binding.root.setOnClickListener {
                itemClickListener.itemClicked(item)
            }
        }
    }

    fun updateList(newList: List<Location>) {
        val diffCallback = LocationsDiffCallback(locations, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        locations.clear()
        locations.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}

interface ItemClickListener {
    fun itemClicked(location: Location)
}
