package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Category(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: Icon? = null,

    @field:SerializedName("id")
    val id: Int? = null
) {
    fun getImageUrl() = icon?.getImageUrl() ?: ""
}
