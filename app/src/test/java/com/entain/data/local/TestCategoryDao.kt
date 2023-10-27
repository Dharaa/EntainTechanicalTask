package com.entain.data.local

import com.entain.data.datasource.FakeDataSource
import com.entain.data.datasource.getSelectedCategory
import com.entain.data.datasource.getSingleCategory
import com.entain.data.datasource.local.CategoryDao
import com.entain.data.datasource.local.CategoryEntity
import com.entain.data.datasource.local.asExternalModel
import com.entain.domain.model.CategoryModel
import com.entain.domain.model.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class TestCategoryDao : CategoryDao {

    private var entitiesMutableFlow = MutableStateFlow(
        emptyList<CategoryEntity>()
    )

    private var categoryMutableFlow = MutableStateFlow(
        getSingleCategory()
    )

    override fun getCategories(): Flow<List<CategoryEntity>> = flow {
        emit(FakeDataSource.localCategoryList.map(CategoryModel::asEntity))
    }

    override fun getSelectedCategories(): Flow<List<String>> = flow {
        emit(getSelectedCategory())
    }

    override suspend fun upsertAll(categories: List<CategoryEntity>) =
        entitiesMutableFlow.update { categories }

    override suspend fun updateCategory(categoryId: String, isChecked: Boolean) {
        categoryMutableFlow.update {
            CategoryEntity(it.categoryName, categoryId, isChecked).asExternalModel()
        }
    }
}