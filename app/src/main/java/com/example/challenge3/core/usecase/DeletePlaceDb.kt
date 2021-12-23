package com.example.challenge3.core.usecase

import com.example.challenge3.core.data.AppRepository

class DeletePlaceDb(private val appRepository: AppRepository) {
    operator fun invoke() = appRepository.deletePlacesDb()
}