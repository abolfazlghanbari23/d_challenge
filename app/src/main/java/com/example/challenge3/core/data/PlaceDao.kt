package com.example.challenge3.core.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.challenge3.core.domain.Place
import io.reactivex.Completable

@Dao
interface PlaceDao : BaseDao<Place> {

    @Query("SELECT * FROM place_table")
    abstract fun getAllPlaces(): LiveData<List<Place>>

    @Query("DELETE FROM place_table")
    abstract fun deleteAllPlaces()
}