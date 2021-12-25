package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Circle(

    @field:SerializedName("center")
    val center: Center? = null,

    @field:SerializedName("radius")
    val radius: Int? = null
)
