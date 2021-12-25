package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Context(

    @field:SerializedName("geo_bounds")
    val geoBounds: GeoBounds? = null
)
