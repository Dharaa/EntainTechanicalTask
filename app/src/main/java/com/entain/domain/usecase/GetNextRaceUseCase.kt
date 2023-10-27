package com.entain.domain.usecase

import com.entain.core.Resource
import com.entain.domain.model.NextRace
import com.entain.domain.repository.NextRaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNextRaceUseCase @Inject constructor(
    private val nextRaceRepository: NextRaceRepository
) {
    operator fun invoke() : Flow<Resource<List<NextRace>>> {
        return nextRaceRepository.getRace()
    }
}