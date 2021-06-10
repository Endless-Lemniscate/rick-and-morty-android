package com.github.endless.lemniscate.rickandmorty.data.network.models.character

data class CharactersResponse (
	val info : Info,
	val results : List<Results>
)