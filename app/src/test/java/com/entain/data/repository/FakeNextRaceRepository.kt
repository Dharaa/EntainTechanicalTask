package com.entain.data.repository

import com.entain.core.Resource
import com.entain.data.datasource.FakeLocalDataSource
import com.entain.domain.model.CategoryModel
import com.entain.domain.model.NextRace
import com.entain.domain.repository.NextRaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNextRaceRepository : NextRaceRepository{

    private val nextRace = mutableListOf<NextRace>()
    private val categoryModel = mutableListOf<CategoryModel>()
    private val selectedCategory = mutableListOf<String>()
    private val localDataSource = FakeLocalDataSource()

    override fun getRace(): Flow<Resource<List<NextRace>>> {
        return flow { emit(Resource.Success(nextRace)) }
    }

    override fun getCategory(): Flow<List<CategoryModel>> {
        return flow { emit(categoryModel) }
    }

    override fun getSelectedCategoryId(): Flow<List<String>> {
        return flow { emit(selectedCategory) }
    }

    override suspend fun insertCategory() {
        categoryModel.addAll(localDataSource.getCategoryList())
    }

    override suspend fun updateValue(categoryId: String, isChecked: Boolean) {

    }
}