package com.github.endless.lemniscate.rickandmorty.data.repositories

import com.github.endless.lemniscate.rickandmorty.data.network.RickAndMortyApiService
import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import com.github.endless.lemniscate.rickandmorty.domain.repositories.LocationsRepository
import io.reactivex.Observable

class LocationsRepositoryImpl(private val api: RickAndMortyApiService): LocationsRepository {

    override fun getAllLocations(): Observable<List<Location>> {
        val response = api.getAllLocations()
        return response.map { resp ->
            resp.results.map {
                it.created
                Location(
                    id = it.id,
                    name = it.name,
                    dimension = it.dimension,
                    type = it.type
                )
            }
        }
    }

}