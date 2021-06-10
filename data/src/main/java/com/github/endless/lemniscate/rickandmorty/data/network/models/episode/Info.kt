package com.github.endless.lemniscate.rickandmorty.data.network.models.episode

data class Info (
	val count : Int,
	val pages : Int,
	val next : String,
	val prev : String
)