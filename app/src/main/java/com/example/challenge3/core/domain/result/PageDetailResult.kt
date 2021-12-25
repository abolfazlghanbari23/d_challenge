package com.example.challenge3.core.domain.result

import com.example.challenge3.core.domain.PlaceDetails

sealed class PageDetailResult {
    data class Success(val value: PlaceDetails): PageDetailResult()
    data class Failure(val message: String?, val throwable: Throwable?): PageDetailResult()
}