package com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.endless.lemniscate.rickandmorty.domain.models.Episode
import com.github.endless.lemniscate.rickandmorty.domain.usecases.GetAllEpisodesUseCase
import com.github.endless.lemniscate.rickandmorty.presentation.App
import com.github.endless.lemniscate.rickandmorty.presentation.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class EpisodesListViewModel: ViewModel() {

    private var page = 1

    @Inject
    lateinit var useCase: GetAllEpisodesUseCase

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        App.applicationComponent.inject(this)
        getEpisodes()
    }

    @SuppressLint("CheckResult")
    fun getEpisodes(refresh: Boolean = false) {
        if (refresh) {
            _isRefreshing.value = true
            page = 1
        } else {
            _isLoading.value = true
        }
        useCase.getAllEpisodes(page)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    _episodes.postValue(addPageToList(list))
                    _isRefreshing.postValue(false)
                    _isLoading.postValue(false)
                    if (refresh) showMessage("Success!")
                },
                { throwable ->
                    if(throwable is HttpException) {
                        if(throwable.code() != 404) {
                            throwable.printStackTrace()
                            showMessage("Error occurred")
                        }
                    } else {
                        throwable.printStackTrace()
                        showMessage("Error occurred")
                    }
                    _isRefreshing.postValue(false)
                    _isLoading.postValue(false)
                }
            )
    }

    private fun addPageToList(newPage: List<Episode>) : List<Episode> {
        if(page == 1) {
            page++
            return newPage
        }
        page++
        val list = _episodes.value!!.toMutableList()
        list.addAll(newPage)
        return list
    }

    private fun showMessage(message: String) {
        _message.postValue(Event(message))
    }

}