package com.entain.data.datasource

import com.entain.data.remote.model.NextRaceResponse
import com.entain.domain.datasource.NextRaceDataSource

class FakeNextRaceDataSource : NextRaceDataSource{
    
    override suspend fun getRace(): NextRaceResponse {
        return FakeDataSource.nextRaceResponse
    }
}