package com.entain.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.entain.data.datasource.local.CategoryDao
import com.entain.data.datasource.local.CategoryEntity
import com.entain.data.datasource.local.RaceDatabase
import com.entain.data.datasource.local.asExternalModel
import com.entain.domain.model.CategoryModel
import com.entain.domain.model.asEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@SmallTest
class CategoryDaoTest {

    private lateinit var database: RaceDatabase
    private lateinit var dao: CategoryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RaceDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.categoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertCategory() = runTest {
        val categoryList: List<CategoryModel> = arrayListOf()

        categoryList.plus(
            CategoryModel(
                "HORSE",
                "4a2788f8-e825-4d36-9894-efd4baf1cfae", false
            )
        )
        dao.upsertAll(categoryList.map(CategoryModel::asEntity))
        val savedCategoryList = dao.getCategories()

        assertEquals(savedCategoryList.map {
            it.map(CategoryEntity::asExternalModel)
        }.first(), categoryList)
    }
}