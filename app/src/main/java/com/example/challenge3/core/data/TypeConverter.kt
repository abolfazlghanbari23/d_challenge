package com.example.challenge3.core.data

import androidx.room.TypeConverter
import com.example.challenge3.core.domain.Category
import com.example.challenge3.core.domain.Geocodes
import com.example.challenge3.core.domain.Location
import com.example.challenge3.core.domain.RelatedPlaces
import com.google.gson.Gson

class TypeConverter() {

    private val gson = Gson()

    @TypeConverter
    fun jsonToRelatedPlaces(json: String): RelatedPlaces {
        return gson.fromJson(json, RelatedPlaces::class.java)
    }

    @TypeConverter
    fun relatedPlacesToJson(relatedPlaces: RelatedPlaces): String {
        return gson.toJson(relatedPlaces)
    }

    @TypeConverter
    fun jsonToGeocodes(json: String): Geocodes {
        return gson.fromJson(json, Geocodes::class.java)
    }

    @TypeConverter
    fun geocodesToJson(geocodes: Geocodes): String {
        return gson.toJson(geocodes)
    }

    @TypeConverter
    fun jsonToLocation(json: String): Location {
        return gson.fromJson(json, Location::class.java)
    }

    @TypeConverter
    fun locationToJson(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun jsonToCategory(json: String): Category {
        return gson.fromJson(json, Category::class.java)
    }

    @TypeConverter
    fun categoryToJson(category: Category): String {
        return gson.toJson(category)
    }

}