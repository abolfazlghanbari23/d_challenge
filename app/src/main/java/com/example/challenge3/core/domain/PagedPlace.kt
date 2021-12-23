package com.example.challenge3.core.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PagedPlace(

    @field:SerializedName("context")
    val context: Context? = null,

    @field:SerializedName("results")
    val results: List<Place?>? = null
)

@Entity(tableName = "place_table")
data class Place(

    @SerializedName("fsq_id")
    @PrimaryKey(autoGenerate = false)
    val fsqId: String,

    @field:SerializedName("distance")
    val distance: Int? = null,

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

//    @field:SerializedName("categories")
//    val categories: List<Category?>? = null
)

data class Center(

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
)

data class Context(

    @field:SerializedName("geo_bounds")
    val geoBounds: GeoBounds? = null
)

data class Main(

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
)

data class GeoBounds(

    @field:SerializedName("circle")
    val circle: Circle? = null
)

data class Circle(

    @field:SerializedName("center")
    val center: Center? = null,

    @field:SerializedName("radius")
    val radius: Int? = null
)

data class Icon(

    @field:SerializedName("prefix")
    val prefix: String? = null,

    @field:SerializedName("suffix")
    val suffix: String? = null
)

data class RelatedPlaces(

    @field:SerializedName("parent")
    val parent: Parent? = null,

    @field:SerializedName("children")
    val children: List<Child?>? = null
)

data class Category(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: Icon? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class Geocodes(

    @field:SerializedName("main")
    val main: Main? = null
)

data class Parent(

    @field:SerializedName("fsq_id")
    val fsqId: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)

data class Child(

    @field:SerializedName("fsq_id")
    val fsqId: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)

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
