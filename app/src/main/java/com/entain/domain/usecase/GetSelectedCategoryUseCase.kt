package com.entain.domain.usecase

import com.entain.domain.repository.NextRaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedCategoryUseCase @Inject constructor(
    private val nextRaceRepository: NextRaceRepository
) {
    operator fun invoke() : Flow<List<String>> {
        return nextRaceRepository.getSelectedCategoryId()
    }
}