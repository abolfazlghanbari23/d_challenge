package com.example.challenge3.ui.fragment.detail

import androidx.lifecycle.MutableLiveData
import com.example.challenge3.base.BaseViewModel
import com.example.challenge3.core.domain.PlaceDetails
import com.example.challenge3.core.domain.PlaceImage
import com.example.challenge3.core.usecase.GetPlaceDetailsUseCase
import com.example.challenge3.core.usecase.GetPlaceImageUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val getPlaceImageUseCase: GetPlaceImageUseCase
) : BaseViewModel() {

    val progressBarLiveData = MutableLiveData<Boolean>()
    val placeDetailsLiveData = MutableLiveData<PlaceDetails>()
    val errorLiveData = MutableLiveData<Boolean>()

    val placeImageLiveData = MutableLiveData<List<PlaceImage>>()
    val placeImageErrorLiveData = MutableLiveData<Boolean>()

    fun getPlaceDetails(fsqId: String) {
        progressBarLiveData.value = true
        errorLiveData.value = false
        val handler = CoroutineExceptionHandler {_, throbable ->
            errorLiveData.postValue(true)
            progressBarLiveData.postValue(false)
        }

        CoroutineScope(IO + handler + viewModelJob).launch {
            val details = getPlaceDetailsUseCase.invoke(fsqId)
            placeDetailsLiveData.postValue(details)
            progressBarLiveData.postValue(false)
        }

    }

    fun getPlaceImage(fsqId: String) {
        placeImageErrorLiveData.value = false
        val handler = CoroutineExceptionHandler {_, throbable ->
            placeImageErrorLiveData.postValue(true)
        }
        CoroutineScope(IO + handler + viewModelJob).launch {
            val image = getPlaceImageUseCase.invoke(fsqId)
            placeImageLiveData.value = image
        }

    }
}