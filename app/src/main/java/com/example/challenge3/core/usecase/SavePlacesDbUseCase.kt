package com.example.challenge3.core.usecase

import com.example.challenge3.core.data.AppRepository
import com.example.challenge3.core.domain.Place

class SavePlacesDbUseCase(private val appRepository: AppRepository) {
    operator fun invoke(data: List<Place>) = appRepository.savePlacesDb(data)
}