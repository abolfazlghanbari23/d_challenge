package com.example.challenge3.core.usecase

import com.example.challenge3.core.data.AppRepository

class GetPlaceImageUseCase(private val appRepository: AppRepository) {
    suspend operator fun invoke(fsqId: String) = appRepository.getPlaceImage(fsqId)
}