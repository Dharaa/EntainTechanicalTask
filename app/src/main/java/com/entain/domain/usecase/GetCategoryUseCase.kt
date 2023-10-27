package com.entain.domain.usecase

import com.entain.domain.model.CategoryModel
import com.entain.domain.repository.NextRaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val nextRaceRepository: NextRaceRepository
) {
    operator fun invoke() : Flow<List<CategoryModel>> {
        return nextRaceRepository.getCategory()
    }
}