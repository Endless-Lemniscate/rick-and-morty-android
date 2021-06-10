package com.github.endless.lemniscate.rickandmorty.data.network.models.episode

data class EpisodesResponse (
	val info : Info,
	val results : List<Results>
)