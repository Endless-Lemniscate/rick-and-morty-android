package com.github.endless.lemniscate.rickandmorty.domain.repositories

import com.github.endless.lemniscate.rickandmorty.domain.models.Character
import io.reactivex.Observable

interface CharactersRepository {
    fun getAllCharacters(page: Int): Observable<List<Character>>
}