package com.github.endless.lemniscate.rickandmorty.domain.usecases

import com.github.endless.lemniscate.rickandmorty.domain.models.Character
import com.github.endless.lemniscate.rickandmorty.domain.repositories.CharactersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    fun getAllCharacters(page: Int): Observable<List<Character>> {
        return charactersRepository.getAllCharacters(page)
    }
}