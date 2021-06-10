package com.github.endless.lemniscate.rickandmorty.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.github.endless.lemniscate.rickandmorty.domain.models.Character

@Parcelize
data class CharacterParcelable(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val imageUrl: String
): Parcelable

fun Character.toParcelable() = CharacterParcelable(
    id,
    name,
    species,
    status,
    gender,
    imageUrl
)