package com.entain.data.remote.model

import com.squareup.moshi.Json

data class NextRaceResponse(

	@field:Json(name = "data")
	val data: Data? = null,

	@field:Json(name = "message")
	val message: String? = null,

	@field:Json(name = "status")
	val status: Int? = null
)

data class Data(
	@field:Json(name = "race_summaries")
	val raceSummaries: Map<String, NextRaceDto>,
)

data class NextRaceDto(
	@field:Json(name = "race_id")
	val raceId: String? = null,

	@field:Json(name = "race_name")
	val raceName: String? = null,

	@field:Json(name = "race_number")
	val raceNumber: Int? = null,

	@field:Json(name = "meeting_id")
	val meetingId: String? = null,

	@field:Json(name = "meeting_name")
	val meetingName: String? = null,

	@field:Json(name = "category_id")
	val categoryId: String? = null,

	@field:Json(name = "advertised_start")
	val advertisedStart: AdvertisedStart,

)

data class AdvertisedStart(
	@field:Json(name = "seconds")
	val seconds: Long? = null,
)