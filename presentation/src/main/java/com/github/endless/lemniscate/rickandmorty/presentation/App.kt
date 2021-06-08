package com.github.endless.lemniscate.rickandmorty.presentation

import android.app.Application
import com.github.endless.lemniscate.rickandmorty.presentation.di.ApplicationComponent
import com.github.endless.lemniscate.rickandmorty.presentation.di.DaggerApplicationComponent
import com.github.endless.lemniscate.rickandmorty.presentation.di.modules.ContextModule

class App: Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }


}