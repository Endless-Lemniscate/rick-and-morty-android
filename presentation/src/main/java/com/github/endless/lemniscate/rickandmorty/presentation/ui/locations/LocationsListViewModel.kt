package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import com.github.endless.lemniscate.rickandmorty.domain.usecases.GetAllLocationsUseCase
import com.github.endless.lemniscate.rickandmorty.presentation.App
import com.github.endless.lemniscate.rickandmorty.presentation.ui.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationsListViewModel: ViewModel() {

    var page = 1

    @Inject
    lateinit var useCase: GetAllLocationsUseCase

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        App.applicationComponent.inject(this)
        getLocations()
    }

    @SuppressLint("CheckResult")
    fun getLocations(refresh: Boolean = false) {
        if (refresh) {
            _isRefreshing.value = true
            page = 1
        }
        useCase.getAllLocations(page)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    _locations.postValue(addPageToList(list))
                    _isRefreshing.postValue(false)
                    if (refresh) showMessage("Success!")
                },
                { throwable ->
                    throwable.printStackTrace()
                    _isRefreshing.postValue(false)
                    showMessage("Error occurred")
                }
            )
    }

    private fun addPageToList(newPage: List<Location>) : List<Location> {
        if(page == 1) {
            page++
            return newPage
        }
        page++
        val list = _locations.value!!.toMutableList()
        list.addAll(newPage)
        return list
    }

    private fun showMessage(message: String) {
        _message.postValue(Event(message))
    }

}