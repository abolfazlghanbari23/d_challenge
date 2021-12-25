package com.example.challenge3.core.usecase

import com.example.challenge3.core.data.AppRepository

class DeletePlaceDbUseCase(private val appRepository: AppRepository) {
    suspend operator fun invoke() = appRepository.deletePlacesDb()
}