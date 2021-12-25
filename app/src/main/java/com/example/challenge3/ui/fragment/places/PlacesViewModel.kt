package com.example.challenge3.ui.fragment.places

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.challenge3.adapter.Pager
import com.example.challenge3.base.BaseViewModel
import com.example.challenge3.base.rx.MySingleObserver
import com.example.challenge3.core.domain.PagedPlace
import com.example.challenge3.core.usecase.DeletePlaceDb
import com.example.challenge3.core.usecase.GetPlacesDbUseCase
import com.example.challenge3.core.usecase.GetPlacesServerUseCase
import com.example.challenge3.core.usecase.SavePlacesDbUseCase
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlacesViewModel @Inject constructor(
    private val getPlacesServerUseCase: GetPlacesServerUseCase,
    private val getPlacesDbUseCase: GetPlacesDbUseCase,
    private val deletePlacesDbUseCase: GetPlacesDbUseCase,
    private val savePlacesDbUseCase: SavePlacesDbUseCase
) : BaseViewModel() {

    val progressBarLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Boolean>()

    var pager = Pager()

    fun getAllPlaces() = getPlacesDbUseCase.invoke()

    fun fetchPlaces(page: Int, coordinate: String) {
        progressBarLiveData.value = true
        errorLiveData.value = false
        pager.isListFinished = false
        pager.isLoading = true

        val handler = CoroutineExceptionHandler { _, throbable ->
            errorLiveData.postValue(true)
        }

        CoroutineScope(IO + handler + viewModelJob).launch {
            if (page == 0)
                deletePlacesDbUseCase.invoke()

            val res = getPlacesServerUseCase.invoke(page, coordinate)
            savePlacesDbUseCase.invoke(res.results)
            pager.index++
            pager.isLoading = false
            if (res.results.isEmpty())
                pager.isListFinished = true
            progressBarLiveData.postValue(false)
        }

    }

}