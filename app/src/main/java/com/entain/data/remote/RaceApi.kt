package com.entain.data.remote

import com.entain.data.remote.model.NextRaceResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Retrofit service object for creating api calls
 */
interface RaceApi {

    @Headers("Content-type: application/json")
    @GET("racing/")
    suspend fun getRace(
        @Query("method") method: String = METHOD_NAME,
        @Query("count") count: String = COUNT
    ): NextRaceResponse

    companion object {
        const val BASE_URL = "https://api.neds.com.au/rest/v1/"
        const val METHOD_NAME = "nextraces"
        const val COUNT = "10"
    }
}