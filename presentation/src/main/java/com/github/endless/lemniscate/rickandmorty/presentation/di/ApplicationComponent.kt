package com.github.endless.lemniscate.rickandmorty.presentation.di

import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.ContextModule
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.RepositoryModule
import com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.LocationsListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ContextModule::class])
interface ApplicationComponent {

    fun inject(locationsListViewModel: LocationsListViewModel)
}