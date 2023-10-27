package com.entain.data.datasource

import com.entain.data.remote.RaceApi
import com.entain.data.remote.model.NextRaceResponse
import com.entain.domain.datasource.NextRaceDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NextRaceDataSourceImpl @Inject constructor(
    private val raceApi: RaceApi,
    private val ioDispatcher: CoroutineDispatcher
) : NextRaceDataSource {

    /**
     * gets the Next race list
     */
    override suspend fun getRace(): NextRaceResponse =
        withContext(ioDispatcher) {
            raceApi.getRace()
        }
}