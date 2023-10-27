package com.entain.data.datasource

import com.entain.domain.datasource.LocalDataSource
import com.entain.domain.model.CategoryModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor() : LocalDataSource {

    /**
     * used static data for demo purpose only as per given in task
     */
    override fun getCategoryList(): List<CategoryModel> {
        val items: MutableList<CategoryModel> = ArrayList()
        items.add(CategoryModel("HORSE",
            "4a2788f8-e825-4d36-9894-efd4baf1cfae", false))
        items.add(CategoryModel("HARNESS",
            "161d9be2-e909-4326-8c2c-35ed71fb460b", false))
        items.add(CategoryModel("GREYHOUND",
            "9daef0d7-bf3c-4f50-921d-8e818c60fe61", false))
        return items
    }
}