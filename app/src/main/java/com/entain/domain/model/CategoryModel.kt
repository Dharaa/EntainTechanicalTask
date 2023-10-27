package com.entain.domain.model

import com.entain.data.datasource.local.CategoryEntity

data class CategoryModel(
    val categoryName: String = "",
    val categoryId: String = "",
    val isSelected: Boolean = false
)

fun CategoryModel.asEntity() = CategoryEntity(
    categoryName = categoryName,
    categoryId = categoryId,
    isSelected = isSelected
)