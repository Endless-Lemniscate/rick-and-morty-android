package com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.endless.lemniscate.rickandmorty.R
import com.github.endless.lemniscate.rickandmorty.databinding.EpisodeItemLayoutBinding
import com.github.endless.lemniscate.rickandmorty.domain.models.Episode

class EpisodesRecyclerAdapter(private val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<EpisodesRecyclerAdapter.ViewHolder>() {

    private val episodes = ArrayList<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EpisodeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bind(episode)
    }

    override fun getItemCount(): Int = episodes.size

    inner class ViewHolder(private val binding: EpisodeItemLayoutBinding,
                           private val context: Context): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Episode) {
            binding.apply {
                name.text = item.name
                airDate.text = context.getString(R.string.air_date_holder, item.air_date)
                episode.text = context.getString(R.string.episode_holder, item.episode)
            }
            binding.root.setOnClickListener {
                itemClickListener.itemClicked(item)
            }
        }
    }

    fun updateList(newList: List<Episode>) {
        val diffCallback = EpisodesDiffCallback(episodes, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        episodes.clear()
        episodes.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}

interface ItemClickListener {
    fun itemClicked(episode: Episode)
}
