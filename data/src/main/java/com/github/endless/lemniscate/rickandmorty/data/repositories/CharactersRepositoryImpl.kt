package com.github.endless.lemniscate.rickandmorty.data.repositories

import com.github.endless.lemniscate.rickandmorty.data.network.RickAndMortyApiService
import com.github.endless.lemniscate.rickandmorty.domain.models.Character
import com.github.endless.lemniscate.rickandmorty.domain.repositories.CharactersRepository
import io.reactivex.Observable

class CharactersRepositoryImpl(private val api: RickAndMortyApiService): CharactersRepository {

    override fun getAllCharacters(page: Int): Observable<List<Character>> {
        val response = api.getAllCharacters(page)
        return response.map { resp ->
            resp.results.map {
                Character(
                    id = it.id,
                    name = it.name,
                    species = it.species,
                    status = it.status,
                    gender = it.gender,
                    imageUrl = it.image
                )
            }
        }
    }
}