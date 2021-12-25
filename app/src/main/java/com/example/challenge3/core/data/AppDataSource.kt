package com.example.challenge3.core.data

import com.example.challenge3.core.domain.PagedPlace
import com.example.challenge3.core.domain.Place
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AppDataSource {

    @GET("v3/places/search")
    fun getPlaces(
        @Query("page") page: Int,
        @Query("ll") coordinates: String,
        @Query("limit") limit: Int = 50,
        @Query("client_id") clientId: String = "OPGXR02CT3P4HINOPVLBSXJ0YQNCB13XAZLK2I34VZ2OCYVR",
        @Query("client_secret") clientSecret: String = "3IMCNFUFLPICAXGPN2PMQGHMQK2D3ZTV3YUSPIYJHMR0KIWI",
        @Header("Authorization") auth: String = "fsq3APQuknuijW2iZLITIrLEtMj8bmjq47fBErYYBAssOP8="
    ): Single<PagedPlace>
}