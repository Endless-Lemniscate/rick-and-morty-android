package com.github.endless.lemniscate.rickandmorty.domain.repositories

import com.github.endless.lemniscate.rickandmorty.domain.models.Episode
import io.reactivex.Observable

interface EpisodesRepository {
    fun getAllEpisodes(page: Int): Observable<List<Episode>>
}