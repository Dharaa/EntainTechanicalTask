package com.entain.domain.datasource

import com.entain.domain.model.CategoryModel

fun interface LocalDataSource {
    fun getCategoryList(): List<CategoryModel>
}