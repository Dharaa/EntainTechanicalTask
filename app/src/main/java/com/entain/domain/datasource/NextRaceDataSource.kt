package com.entain.domain.datasource

import com.entain.data.remote.model.NextRaceResponse

fun interface NextRaceDataSource {
    suspend fun getRace(): NextRaceResponse
}