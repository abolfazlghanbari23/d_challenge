package com.example.challenge3.core.usecase

import com.example.challenge3.core.data.AppRepository

class GetPlacesServerUseCase(private val appRepository: AppRepository) {
    operator fun invoke(page: Int, coordinates: String) =
        appRepository.getPlaces(page, coordinates)
}