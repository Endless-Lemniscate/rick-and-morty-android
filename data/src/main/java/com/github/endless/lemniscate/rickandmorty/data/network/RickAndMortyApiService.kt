package com.github.endless.lemniscate.rickandmorty.data.network

import com.github.endless.lemniscate.rickandmorty.data.network.models.character.CharactersResponse
import com.github.endless.lemniscate.rickandmorty.data.network.models.episode.EpisodesResponse
import com.github.endless.lemniscate.rickandmorty.data.network.models.location.LocationsResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RickAndMortyApiService {

    @GET("location")
    fun getAllLocations(@Query("page") page: Int): Observable<LocationsResponse>

    @GET("character")
    fun getAllCharacters(@Query("page") page: Int): Observable<CharactersResponse>

    @GET("episode")
    fun getAllEpisodes(@Query("page") page: Int): Observable<EpisodesResponse>

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