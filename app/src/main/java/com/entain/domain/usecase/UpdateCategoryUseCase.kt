package com.entain.domain.usecase

import com.entain.domain.repository.NextRaceRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val nextRaceRepository: NextRaceRepository
) {

    suspend operator fun invoke(categoryId: String, isChecked: Boolean) {
        return nextRaceRepository.updateValue(categoryId = categoryId, isChecked = isChecked)
    }
}