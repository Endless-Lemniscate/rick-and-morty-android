package com.github.endless.lemniscate.rickandmorty.presentation.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class GlideModule {

    @Provides
    fun provideGlide(context: Context): RequestManager {
        return Glide.with(context)
    }
}