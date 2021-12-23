package com.example.challenge3.core.data

import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDataSource: AppDataSource,
    private val appDatabase: AppDatabase
) {
    fun getPlaces(page: Int, coordinates: String) = appDataSource.getPlaces(page, coordinates)

    fun savePlacesDb() = appDatabase.FormDao().getAllPlaces()

    fun deletePlacesDb() = appDatabase.FormDao().deleteAllPlaces()
}