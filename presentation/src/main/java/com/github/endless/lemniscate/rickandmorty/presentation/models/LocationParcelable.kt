package com.github.endless.lemniscate.rickandmorty.presentation.models

import android.os.Parcelable
import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationParcelable (
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
): Parcelable

fun Location.toParcelable(): LocationParcelable = LocationParcelable(id, name, type, dimension)
