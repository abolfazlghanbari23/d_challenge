package com.example.challenge3.core.domain

import com.example.challenge3.core.domain.sub.Context
import com.google.gson.annotations.SerializedName

data class PagedPlace(

    @field:SerializedName("context")
    val context: Context? = null,

    @field:SerializedName("results")
    val results: List<Place>
)

