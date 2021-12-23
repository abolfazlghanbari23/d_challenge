package com.example.challenge3.core.data

import com.example.challenge3.core.domain.PagedPlace
import com.example.challenge3.core.domain.Place
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppDataSource {

    @GET("v3/places/search")
    fun getPlaces(
        @Query("limit") page: Int,
        @Query("ll") coordinates: String
    ): Single<PagedPlace>
}