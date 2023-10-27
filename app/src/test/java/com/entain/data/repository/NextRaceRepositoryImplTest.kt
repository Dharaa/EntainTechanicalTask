package com.entain.data.repository

import com.entain.data.datasource.FakeDataSource
import com.entain.data.datasource.FakeLocalDataSource
import com.entain.data.datasource.FakeNextRaceDataSource
import com.entain.data.datasource.local.CategoryEntity
import com.entain.data.datasource.local.asExternalModel
import com.entain.data.local.TestCategoryDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class NextRaceRepositoryImplTest {

    private lateinit var nextRaceRepository: NextRaceRepositoryImpl

    private lateinit var dao: TestCategoryDao
    private lateinit var fakeNextRaceDataSource: FakeNextRaceDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        dao = TestCategoryDao()
        fakeNextRaceDataSource = FakeNextRaceDataSource()
        nextRaceRepository = NextRaceRepositoryImpl(
            fakeNextRaceDataSource,
            FakeLocalDataSource(),
            dao,
            testDispatcher
        )
    }

    @Test
    fun getCategories() = runTest {
        val categoryList = dao.getCategories().map {
            it.map(CategoryEntity::asExternalModel)
        }.first()
        assertEquals(FakeDataSource.localCategoryList, categoryList)
    }

    @Test
    fun getSelectedCategory() = runTest {
        val categoryList = dao.getSelectedCategories().first()
        assertEquals(FakeDataSource.selectedCategories, categoryList)
    }

}