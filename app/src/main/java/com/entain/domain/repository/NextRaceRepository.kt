package com.entain.domain.repository

import com.entain.core.Resource
import com.entain.domain.model.CategoryModel
import com.entain.domain.model.NextRace
import kotlinx.coroutines.flow.Flow

interface NextRaceRepository {
    fun getRace(): Flow<Resource<List<NextRace>>>
    fun getCategory(): Flow<List<CategoryModel>>
    fun getSelectedCategoryId(): Flow<List<String>>
    suspend fun insertCategory()
    suspend fun updateValue(categoryId: String, isChecked: Boolean)
}