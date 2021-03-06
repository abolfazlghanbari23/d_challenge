package com.example.challenge3.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.challenge3.core.domain.Place

@Database(entities = [Place::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}