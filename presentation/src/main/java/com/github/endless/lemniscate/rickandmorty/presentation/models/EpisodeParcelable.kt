package com.github.endless.lemniscate.rickandmorty.presentation.models

import android.os.Parcelable
import com.github.endless.lemniscate.rickandmorty.domain.models.Episode
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeParcelable(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String
): Parcelable

fun Episode.toParcelable() = EpisodeParcelable(
    id,
    name,
    air_date,
    episode
)