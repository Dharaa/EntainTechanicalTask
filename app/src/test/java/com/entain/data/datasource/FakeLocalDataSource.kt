package com.entain.data.datasource

import com.entain.domain.datasource.LocalDataSource
import com.entain.domain.model.CategoryModel

class FakeLocalDataSource : LocalDataSource {

    override fun getCategoryList(): List<CategoryModel> {
        return FakeDataSource.localCategoryList
    }
}