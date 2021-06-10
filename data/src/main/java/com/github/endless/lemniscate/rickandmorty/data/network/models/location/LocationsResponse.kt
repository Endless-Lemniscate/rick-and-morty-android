package com.github.endless.lemniscate.rickandmorty.data.network.models.location

data class LocationsResponse (
    val info : Info,
    val results : List<Results>
)