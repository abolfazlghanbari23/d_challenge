package com.example.challenge3.core.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.challenge3.core.domain.Place

@Dao
interface PlaceDao : BaseDao<Place> {

    @Query("SELECT * FROM place_table")
    fun getAllPlaces(): LiveData<List<Place>>

    @Query("DELETE FROM place_table")
    suspend fun deleteAllPlaces()
}