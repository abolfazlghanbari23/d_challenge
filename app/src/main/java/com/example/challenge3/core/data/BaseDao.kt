package com.example.challenge3.core.data

import androidx.room.*
import io.reactivex.Completable

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(data: List<T>)

    @Update
    abstract fun update(data: T)

    @Delete
    abstract fun delete(data: T)
}