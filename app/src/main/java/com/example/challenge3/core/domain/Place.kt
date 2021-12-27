package com.example.challenge3.core.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.challenge3.core.domain.sub.Category
import com.example.challenge3.core.domain.sub.Geocodes
import com.example.challenge3.core.domain.sub.Location
import com.example.challenge3.core.domain.sub.RelatedPlaces
import com.google.gson.annotations.SerializedName

@Entity(tableName = "place_table")
data class Place(

    @SerializedName("fsq_id")
    @PrimaryKey(autoGenerate = false)
    val fsqId: String,

    @field:SerializedName("distance")
    val distance: Int,

    @field:SerializedName("timezone")
    val timezone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("geocodes")
    val geocodes: Geocodes,

    @field:SerializedName("location")
    val location: Location,

    @field:SerializedName("categories")
    val categories: List<Category?>? = null
) {
    fun getGeocodePretty(): String {
        val main = geocodes.main
        return String.format("%.2f", main.longitude) + "/" + String.format("%.2f", main.latitude)
    }

    fun getImageUrl() = "https://api.foursquare.com/v3/places/${fsqId}/photos"
}
