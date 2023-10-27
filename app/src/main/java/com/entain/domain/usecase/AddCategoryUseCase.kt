package com.entain.domain.usecase

import com.entain.domain.repository.NextRaceRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val nextRaceRepository: NextRaceRepository
) {
    suspend operator fun invoke() {
        return nextRaceRepository.insertCategory()
    }
}