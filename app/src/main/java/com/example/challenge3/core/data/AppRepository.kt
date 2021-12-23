package com.example.challenge3.core.data

import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDataSource: AppDataSource,
    private val appDatabase: AppDatabase
) {
    fun getPlaces() = appDatabase.placeDao().getAllPlaces()

    fun getPlaces(page: Int, coordinates: String) = appDataSource.getPlaces(page, coordinates)

    fun savePlacesDb() = appDatabase.placeDao().getAllPlaces()

    fun deletePlacesDb() = appDatabase.placeDao().deleteAllPlaces()
}