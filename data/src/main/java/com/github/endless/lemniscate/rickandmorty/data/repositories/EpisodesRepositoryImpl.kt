package com.github.endless.lemniscate.rickandmorty.data.repositories

import com.github.endless.lemniscate.rickandmorty.data.network.RickAndMortyApiService
import com.github.endless.lemniscate.rickandmorty.domain.models.Episode
import com.github.endless.lemniscate.rickandmorty.domain.repositories.EpisodesRepository
import io.reactivex.Observable

class EpisodesRepositoryImpl(private val api: RickAndMortyApiService): EpisodesRepository {

    override fun getAllEpisodes(page: Int): Observable<List<Episode>> {
        val response = api.getAllEpisodes(page)
        return response.map { resp ->
            resp.results.map {
                Episode(
                    id = it.id,
                    name = it.name,
                    air_date = it.air_date,
                    episode = it.episode
                )
            }
        }
    }
}