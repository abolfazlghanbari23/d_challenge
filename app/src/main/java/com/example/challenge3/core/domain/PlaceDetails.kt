package com.example.challenge3.core.domain

import com.example.challenge3.core.domain.sub.Category
import com.example.challenge3.core.domain.sub.Geocodes
import com.example.challenge3.core.domain.sub.Location
import com.example.challenge3.core.domain.sub.RelatedPlaces
import com.google.gson.annotations.SerializedName

data class PlaceDetails(

    @field:SerializedName("fsq_id")
    val fsqId: String? = null,

    @field:SerializedName("chains")
    val chains: List<Any?>? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("related_places")
    val relatedPlaces: RelatedPlaces? = null,

    @field:SerializedName("geocodes")
    val geocodes: Geocodes? = null,

    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("categories")
    val categories: List<Category?>? = null
)


