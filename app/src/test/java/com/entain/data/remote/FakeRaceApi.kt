package com.entain.data.remote

import com.entain.data.datasource.FakeDataSource
import com.entain.data.remote.model.NextRaceResponse

class FakeRaceApi : RaceApi{
    override suspend fun getRace(method: String, count: String): NextRaceResponse {
        return FakeDataSource.nextRaceResponse
    }
}