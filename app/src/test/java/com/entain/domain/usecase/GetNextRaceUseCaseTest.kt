package com.entain.domain.usecase

import com.entain.data.repository.FakeNextRaceRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNextRaceUseCaseTest {

    private lateinit var getNextRaceUseCase: GetNextRaceUseCase
    private lateinit var fakeRepository: FakeNextRaceRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNextRaceRepository()
        getNextRaceUseCase = GetNextRaceUseCase(fakeRepository)
    }

    @Test
    fun getNextRace() = runTest {
        fakeRepository.getRace()
    }
}