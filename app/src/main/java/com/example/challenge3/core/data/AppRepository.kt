package com.example.challenge3.core.data

import com.example.challenge3.core.domain.Place
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDataSource: AppDataSource,
    private val appDatabase: AppDatabase
) {
    fun getPlaces() = appDatabase.placeDao().getAllPlaces()

    suspend fun getPlaces(page: Int, coordinates: String) = appDataSource.getPlaces(page, coordinates)

    suspend fun savePlacesDb(data: List<Place>) = appDatabase.placeDao().insert(data)

    suspend fun deletePlacesDb() = appDatabase.placeDao().deleteAllPlaces()

    suspend fun getPlaceDetails(fsqId: String) = appDataSource.getPlaceDetail(fsqId)
}