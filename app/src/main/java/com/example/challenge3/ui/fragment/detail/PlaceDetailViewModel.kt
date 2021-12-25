package com.example.challenge3.ui.fragment.detail

import androidx.lifecycle.MutableLiveData
import com.example.challenge3.base.BaseViewModel
import com.example.challenge3.base.rx.MySingleObserver
import com.example.challenge3.core.domain.PlaceDetails
import com.example.challenge3.core.usecase.GetPlaceDetailsUseCase
import javax.inject.Inject

class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase
) : BaseViewModel() {

    val progressBarLiveData = MutableLiveData<Boolean>()
    val placeDetailsLiveData = MutableLiveData<PlaceDetails>()

    fun getPlaceDetails(fsqId: String) {
        progressBarLiveData.value = true
        getPlaceDetailsUseCase.invoke(fsqId)
            .doOnEvent { _, _ -> progressBarLiveData.value = false }
            .subscribe(object : MySingleObserver<PlaceDetails>(compositeDisposable) {
                override fun onSuccess(placeDetails: PlaceDetails) {
                    placeDetailsLiveData.value = placeDetails
                }

                override fun onError(e: Throwable) {

                }

            })
    }
}