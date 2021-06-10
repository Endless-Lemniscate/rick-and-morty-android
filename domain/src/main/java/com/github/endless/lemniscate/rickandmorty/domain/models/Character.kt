package com.github.endless.lemniscate.rickandmorty.domain.models

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val imageUrl: String
)