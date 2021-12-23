package com.example.challenge3.core.data

class AppRepository constructor(
    private val appDataSource: AppDataSource,
    private val appDatabase: AppDatabase
) {
    fun getPlaces(page: Int, coordinates: String) = appDataSource.getPlaces(page, coordinates)

    fun savePlacesDb() = appDatabase.FormDao().getAllPlaces()

    fun deletePlacesDb() = appDatabase.FormDao().deleteAllPlaces()
}