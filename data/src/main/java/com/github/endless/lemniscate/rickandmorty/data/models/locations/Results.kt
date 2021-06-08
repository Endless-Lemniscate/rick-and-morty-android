package com.github.endless.lemniscate.rickandmorty.data.models.locations

data class Results (
    val id : Int,
    val name : String,
    val type : String,
    val dimension : String,
    val residents : List<String>,
    val url : String,
    val created : String
)