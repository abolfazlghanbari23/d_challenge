package com.example.challenge3.core.usecase

import com.example.challenge3.core.data.AppRepository

class GetPlaceDetailsUseCase(private val appRepository: AppRepository) {
    operator fun invoke(fsqId: String) = appRepository.getPlaceDetails(fsqId)
}