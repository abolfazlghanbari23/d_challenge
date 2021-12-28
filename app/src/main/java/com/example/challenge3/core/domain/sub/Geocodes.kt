package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Geocodes(

    @field:SerializedName("main")
    val main: Main
) {
    fun getGeocodePretty() =
        String.format("%.2f", main.longitude) + "," + String.format("%.2f", main.latitude)

}
