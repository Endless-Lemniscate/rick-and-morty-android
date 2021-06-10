package com.github.endless.lemniscate.rickandmorty.data.network.models.location

data class Results (
    val id : Int,
    val name : String,
    val type : String,
    val dimension : String,
    val residents : List<String>,
    val url : String,
    val created : String
)