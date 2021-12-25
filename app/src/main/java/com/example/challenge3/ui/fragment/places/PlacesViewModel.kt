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
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PlacesViewModel @Inject constructor(
    private val getPlacesServerUseCase: GetPlacesServerUseCase,
    private val getPlacesDbUseCase: GetPlacesDbUseCase,
    private val deletePlacesDbUseCase: GetPlacesDbUseCase
) : BaseViewModel() {

    val progressBarLiveData = MutableLiveData<Boolean>()

    var pager = Pager()

    fun getAllPlaces() = getPlacesDbUseCase.invoke()

    fun fetchPlaces(page: Int, coordinate: String) {
        progressBarLiveData.value = true
        pager.isListFinished = false
        pager.isLoading = true
        getPlacesServerUseCase.invoke(page, coordinate)
            .doOnEvent { pagedPlace, throwable -> progressBarLiveData.value = false }
            .subscribe(object : MySingleObserver<PagedPlace>(compositeDisposable) {
                override fun onSuccess(t: PagedPlace) {
                    pager.index++
                    pager.isLoading = false
                    if (t.results.isEmpty()) {
                        pager.isListFinished = true
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError: ")
                }

            })
    }

}