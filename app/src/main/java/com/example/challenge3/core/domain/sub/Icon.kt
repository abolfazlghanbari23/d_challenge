package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Icon(

    @field:SerializedName("prefix")
    val prefix: String? = null,

    @field:SerializedName("suffix")
    val suffix: String? = null
)
