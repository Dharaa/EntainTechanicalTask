package com.entain.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.entain.domain.model.CategoryModel

/**
 * Internal model used to represent a category stored locally in a Room database.
 */
@Entity(
    tableName = "category"
)
data class CategoryEntity(
    var categoryName: String,
    @PrimaryKey var categoryId: String,
    var isSelected: Boolean = false,
)

fun CategoryEntity.asExternalModel() = CategoryModel(
    categoryName = categoryName,
    categoryId = categoryId,
    isSelected = isSelected
)