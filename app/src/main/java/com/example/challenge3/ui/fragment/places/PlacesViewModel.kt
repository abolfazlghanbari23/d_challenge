package com.example.challenge3.ui.fragment.places

import androidx.lifecycle.MutableLiveData
import com.example.challenge3.adapter.Pager
import com.example.challenge3.base.BaseViewModel
import com.example.challenge3.core.usecase.DeletePlaceDbUseCase
import com.example.challenge3.core.usecase.GetPlacesDbUseCase
import com.example.challenge3.core.usecase.GetPlacesServerUseCase
import com.example.challenge3.core.usecase.SavePlacesDbUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlacesViewModel @Inject constructor(
    private val getPlacesServerUseCase: GetPlacesServerUseCase,
    private val getPlacesDbUseCase: GetPlacesDbUseCase,
    private val deletePlacesDbUseCaseUseCase: DeletePlaceDbUseCase,
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
            progressBarLiveData.postValue(false)
        }

        CoroutineScope(IO + handler + viewModelJob).launch {

            val res = getPlacesServerUseCase.invoke(page, coordinate)
            if (page == 0)
                deletePlacesDbUseCaseUseCase.invoke()
            savePlacesDbUseCase.invoke(res.results)
            pager.index++
            pager.isLoading = false
            if (res.results.isEmpty())
                pager.isListFinished = true
            progressBarLiveData.postValue(false)
        }

    }

}