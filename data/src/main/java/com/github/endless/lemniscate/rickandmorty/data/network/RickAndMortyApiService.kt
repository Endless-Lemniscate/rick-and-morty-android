package com.github.endless.lemniscate.rickandmorty.data.network

import com.github.endless.lemniscate.rickandmorty.data.models.locations.LocationsResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RickAndMortyApiService {

    @GET("location")
    fun getAllLocations(): Observable<LocationsResponse>

    companion object {
        private val gson = GsonBuilder()
            .create()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        fun get(): RickAndMortyApiService {
            return retrofit.create(RickAndMortyApiService::class.java)
        }

    }
}