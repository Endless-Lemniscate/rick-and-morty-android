package com.github.endless.lemniscate.rickandmorty.domain.usecases

import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import com.github.endless.lemniscate.rickandmorty.domain.repositories.LocationsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllLocationsUseCase @Inject constructor(private val locationsRepository: LocationsRepository) {

    fun getAllLocations(): Observable<List<Location>> {
        return locationsRepository.getAllLocations()
    }
}