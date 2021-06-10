package com.github.endless.lemniscate.rickandmorty.domain.usecases

import com.github.endless.lemniscate.rickandmorty.domain.models.Episode
import com.github.endless.lemniscate.rickandmorty.domain.repositories.EpisodesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllEpisodesUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    fun getAllEpisodes(page: Int): Observable<List<Episode>> {
        return episodesRepository.getAllEpisodes(page)
    }
}