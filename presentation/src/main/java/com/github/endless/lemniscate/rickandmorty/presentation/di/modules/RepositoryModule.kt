package com.github.endless.lemniscate.rickandmorty.presentation.di.modules

import com.github.endless.lemniscate.rickandmorty.data.network.RickAndMortyApiService
import com.github.endless.lemniscate.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.github.endless.lemniscate.rickandmorty.domain.repositories.LocationsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideRemoteHabitRepository(api: RickAndMortyApiService): LocationsRepository {
        return LocationsRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideApiService(): RickAndMortyApiService {
        return RickAndMortyApiService.get()
    }
}
