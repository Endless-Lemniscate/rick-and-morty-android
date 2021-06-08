package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import com.github.endless.lemniscate.rickandmorty.domain.usecases.GetAllLocationsUseCase
import com.github.endless.lemniscate.rickandmorty.presentation.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationsListViewModel: ViewModel() {

    var page = 1

    @Inject
    lateinit var useCase: GetAllLocationsUseCase

    val locations = MutableLiveData<List<Location>>()

    init {
        App.applicationComponent.inject(this)
        getLocations()
    }

    @SuppressLint("CheckResult")
    fun getLocations() {
        useCase.getAllLocations(page)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    locations.postValue(addPageToList(list))
                },
                { throwable ->
                    throwable.printStackTrace()
                }
            )
    }

    private fun addPageToList(newPage: List<Location>) : List<Location> {
        if(page == 1) {
            page++
            return newPage
        }
        page++
        val list = locations.value!!.toMutableList()
        list.addAll(newPage)
        return list
    }

}