package com.example.challenge3.core.domain

import com.google.gson.annotations.SerializedName

data class PagedPlace(

    @field:SerializedName("results")
    val results: List<Place>
)

