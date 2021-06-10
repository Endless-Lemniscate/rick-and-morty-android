package com.github.endless.lemniscate.rickandmorty.presentation.di.modules

import com.github.endless.lemniscate.rickandmorty.data.network.RickAndMortyApiService
import com.github.endless.lemniscate.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.github.endless.lemniscate.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.github.endless.lemniscate.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.github.endless.lemniscate.rickandmorty.domain.repositories.CharactersRepository
import com.github.endless.lemniscate.rickandmorty.domain.repositories.EpisodesRepository
import com.github.endless.lemniscate.rickandmorty.domain.repositories.LocationsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideLocationsRepository(api: RickAndMortyApiService): LocationsRepository {
        return LocationsRepositoryImpl(api)
    }

    @Provides
    fun provideCharactersRepository(api: RickAndMortyApiService): CharactersRepository {
        return CharactersRepositoryImpl(api)
    }

    @Provides
    fun provideEpisodesRepository(api: RickAndMortyApiService): EpisodesRepository {
        return EpisodesRepositoryImpl(api)
    }
}
