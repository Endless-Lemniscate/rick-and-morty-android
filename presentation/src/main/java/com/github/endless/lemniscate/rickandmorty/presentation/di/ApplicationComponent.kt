package com.github.endless.lemniscate.rickandmorty.presentation.di

import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.ApiServiceModule
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.ContextModule
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.RepositoryModule
import com.github.endless.lemniscate.rickandmorty.presentation.ui.characters.CharactersListViewModel
import com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes.EpisodesListViewModel
import com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.LocationsListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ContextModule::class, ApiServiceModule::class])
interface ApplicationComponent {

    fun inject(locationsListViewModel: LocationsListViewModel)

    fun inject(charactersListViewModel: CharactersListViewModel)

    fun inject(episodesListViewModel: EpisodesListViewModel)
}