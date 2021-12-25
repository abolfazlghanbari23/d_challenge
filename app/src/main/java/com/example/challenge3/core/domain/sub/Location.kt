package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Location(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("post_town")
    val postTown: String? = null,

    @field:SerializedName("locality")
    val locality: String? = null,

    @field:SerializedName("postcode")
    val postcode: String? = null,

    @field:SerializedName("cross_street")
    val crossStreet: String? = null,

    @field:SerializedName("region")
    val region: String? = null,

    @field:SerializedName("address_extended")
    val addressExtended: String? = null
)
