package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class GeoBounds(

    @field:SerializedName("circle")
    val circle: Circle? = null
)
