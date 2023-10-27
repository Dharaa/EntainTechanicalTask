package com.entain.data.repository

import com.entain.core.Resource
import com.entain.core.helper.diffTime
import com.entain.data.datasource.local.CategoryDao
import com.entain.data.datasource.local.CategoryEntity
import com.entain.data.datasource.local.asExternalModel
import com.entain.data.mapper.toNextRaceList
import com.entain.data.remote.model.NextRaceDto
import com.entain.domain.datasource.LocalDataSource
import com.entain.domain.datasource.NextRaceDataSource
import com.entain.domain.model.CategoryModel
import com.entain.domain.model.NextRace
import com.entain.domain.model.asEntity
import com.entain.domain.repository.NextRaceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NextRaceRepositoryImpl @Inject constructor(
    private val nextRaceDataSource: NextRaceDataSource,
    private val localDataSource: LocalDataSource,
    private val categoryDao: CategoryDao,
    private val defaultDispatcher: CoroutineDispatcher
) : NextRaceRepository {

    /**
     * get next to go race list from network
     */
    override fun getRace(): Flow<Resource<List<NextRace>>> = flow {
        try {
            emit(Resource.Loading())
            val response = nextRaceDataSource.getRace()
            val nextRaceList = response.data?.raceSummaries
            if (!nextRaceList.isNullOrEmpty()) {
                val result: List<NextRaceDto> = ArrayList<NextRaceDto>(nextRaceList.values)
                val finalResult = result.toNextRaceList().filter {
                    (diffTime((it.raceTime * 1000).minus(System.currentTimeMillis())) > -1)
                }.sortedBy { it.raceTime }
                emit(Resource.Success(finalResult))
            } else {
                emit(Resource.Success(emptyList()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }.flowOn(defaultDispatcher)


    /**
     * get category list from local database
     */
    override fun getCategory(): Flow<List<CategoryModel>> =
        categoryDao.getCategories().map { it.map(CategoryEntity::asExternalModel) }

    /**
     * get selected category ids for filter from local database
     */
    override fun getSelectedCategoryId(): Flow<List<String>> {
        return categoryDao.getSelectedCategories()
    }

    /**
     * insert category list to local database
     */
    override suspend fun insertCategory() {
        categoryDao.upsertAll(
            categories = localDataSource.getCategoryList().map(CategoryModel::asEntity)
        )
    }

    /**
     * update category selection to local database
     */
    override suspend fun updateValue(categoryId: String, isChecked: Boolean) {
        categoryDao.updateCategory(categoryId, isChecked)
    }
}