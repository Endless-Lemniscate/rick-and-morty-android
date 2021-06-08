package com.github.endless.lemniscate.rickandmorty.domain.repositories

import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import io.reactivex.Observable

interface LocationsRepository {
    fun getAllLocations(): Observable<List<Location>>
}