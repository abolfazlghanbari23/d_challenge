package com.example.challenge3.core.data

import com.example.challenge3.BuildConfig
import com.example.challenge3.core.domain.PagedPlace
import com.example.challenge3.core.domain.PlaceDetails
import com.example.challenge3.core.domain.PlaceImage
import com.example.challenge3.core.domain.PlaceSort
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface AppDataSource {

    @GET("v3/places/nearby")
    suspend fun getPlaces(
        @Query("page") page: Int,
        @Query("ll") coordinates: String,
        @Query("limit") limit: Int = 50,
        @Query("sort") sort: String = PlaceSort.DISTANCE.toString(),
        @Header("Authorization") auth: String = BuildConfig.API_KEY
    ): PagedPlace

    @GET("v3/places/{fsqId}")
    suspend fun getPlaceDetail(
        @Path("fsqId") fsqId: String,
        @Header("Authorization") auth: String = BuildConfig.API_KEY
    ): PlaceDetails

    @GET("https://api.foursquare.com/v3/places/{fsqId}/photos")
    suspend fun getPlaceImage(
        @Path("fsqId") fsqId: String,
        @Header("Authorization") auth: String = BuildConfig.API_KEY
    ) : List<PlaceImage>
}