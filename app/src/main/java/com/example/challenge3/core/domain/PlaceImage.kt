package com.example.challenge3.core.domain

import com.google.gson.annotations.SerializedName


data class PlaceImage(

    @field:SerializedName("prefix")
    val prefix: String,

    @field:SerializedName("width")
    val width: Int,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("suffix")
    val suffix: String,

    @field:SerializedName("height")
    val height: Int
) {
    fun getImgUrl() = prefix + "original" +suffix
}
