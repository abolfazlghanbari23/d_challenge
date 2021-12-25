package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Child(

    @field:SerializedName("fsq_id")
    val fsqId: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
