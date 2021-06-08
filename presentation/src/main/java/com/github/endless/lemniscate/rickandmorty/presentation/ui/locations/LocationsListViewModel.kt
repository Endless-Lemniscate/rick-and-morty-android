package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import com.github.endless.lemniscate.rickandmorty.domain.usecases.GetAllLocationsUseCase
import com.github.endless.lemniscate.rickandmorty.presentation.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationsListViewModel: ViewModel() {

    @Inject
    lateinit var useCase: GetAllLocationsUseCase

    val locations = MutableLiveData<List<Location>>()

    init {
        App.applicationComponent.inject(this)
    }

    fun fetch() {
        useCase.getAllLocations()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe( { l -> locations.postValue(l) }, { throwable ->
                Log.e("EROORRRRRRRRRRRRR", "${throwable.message}")
                throwable.printStackTrace()
            })
    }

}