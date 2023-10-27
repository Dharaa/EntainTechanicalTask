package com.entain.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room Database that contains the category record table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class RaceDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object{
        const val DATABASE_NAME = "race_db"
    }
}