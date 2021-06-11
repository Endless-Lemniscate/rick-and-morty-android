package com.github.endless.lemniscate.rickandmorty.presentation.di

import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.ApiServiceModule
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.ContextModule
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.GlideModule
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.RepositoryModule
import com.github.endless.lemniscate.rickandmorty.presentation.ui.characters.CharacterDetailsFragment
import com.github.endless.lemniscate.rickandmorty.presentation.ui.characters.CharactersListViewModel
import com.github.endless.lemniscate.rickandmorty.presentation.ui.characters.recycler.CharactersRecyclerAdapter
import com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes.EpisodesListViewModel
import com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.LocationsListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ContextModule::class, ApiServiceModule::class,
    GlideModule::class])
interface ApplicationComponent {

    fun inject(locationsListViewModel: LocationsListViewModel)

    fun inject(charactersListViewModel: CharactersListViewModel)

    fun inject(episodesListViewModel: EpisodesListViewModel)

    fun inject(charactersRecyclerAdapter: CharactersRecyclerAdapter)

    fun inject(characterDetailsFragment: CharacterDetailsFragment)
}