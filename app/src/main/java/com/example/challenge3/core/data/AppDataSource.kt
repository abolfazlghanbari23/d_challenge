package com.example.challenge3.core.data

import com.example.challenge3.core.domain.PagedPlace
import com.example.challenge3.core.domain.PlaceDetails
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface AppDataSource {

    @GET("v3/places/search")
    suspend fun getPlaces(
        @Query("page") page: Int,
        @Query("ll") coordinates: String,
        @Query("limit") limit: Int = 50,
        @Header("Authorization") auth: String = "fsq3L+UuTwAfODPlKSyv5OtEQj+XzsvNrS1398AqVfmtlHM="
    ): PagedPlace

    @GET("v3/places/{fsqId}")
    suspend fun getPlaceDetail(
        @Path("fsqId") fsqId: String,
        @Header("Authorization") auth: String = "fsq3L+UuTwAfODPlKSyv5OtEQj+XzsvNrS1398AqVfmtlHM="
    ): PlaceDetails
}