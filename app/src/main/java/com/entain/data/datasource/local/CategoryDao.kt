package com.entain.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the Category table.
 */
@Dao
interface CategoryDao {

    /**
     * get list of category records.
     */
    @Query("SELECT * FROM category")
    fun getCategories() : Flow<List<CategoryEntity>>

    /**
     * get list of selected categories id records.
     */
    @Query("SELECT categoryId FROM category WHERE isSelected = 1")
    fun getSelectedCategories() : Flow<List<String>>

    /**
     * Insert or update category in the database. If a category already exists, replace it.
     */
    @Upsert
    suspend fun upsertAll(categories: List<CategoryEntity>)

    /**
     * update category selection.
     */
    @Query("UPDATE category SET isSelected = :isChecked WHERE categoryId = :categoryId")
    suspend fun updateCategory(categoryId: String, isChecked: Boolean)
}