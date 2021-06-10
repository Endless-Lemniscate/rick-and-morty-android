package com.github.endless.lemniscate.rickandmorty.data.network.models.episode

data class Results (
	val id : Int,
	val name : String,
	val air_date : String,
	val episode : String,
	val characters : List<String>,
	val url : String,
	val created : String
)