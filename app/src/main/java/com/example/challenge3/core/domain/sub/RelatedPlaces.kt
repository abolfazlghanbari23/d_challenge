package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class RelatedPlaces(

    @field:SerializedName("parent")
    val parent: Parent? = null,

    @field:SerializedName("children")
    val children: List<Child?>? = null
)
