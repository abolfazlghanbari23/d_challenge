package com.example.challenge3.ui.fragment.places

import com.example.challenge3.base.BaseViewModel
import com.example.challenge3.core.usecase.DeletePlaceDb
import com.example.challenge3.core.usecase.GetPlacesDbUseCase
import com.example.challenge3.core.usecase.GetPlacesServerUseCase
import javax.inject.Inject

class PlacesViewModel @Inject constructor(
    private val getPlacesServerUseCase: GetPlacesServerUseCase,
    private val getPlacesDbUseCase: GetPlacesDbUseCase,
    private val deletePlaceDb: DeletePlaceDb
) : BaseViewModel() {

}