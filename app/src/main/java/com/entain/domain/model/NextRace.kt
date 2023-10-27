package com.entain.domain.model

data class NextRace(
    val raceId: String = "",
    val raceName: String = "",
    val raceTime: Long = 0,
    val raceNumber: Int = 0,
    val meetingId: String = "",
    val meetingName: String = "",
    val categoryId: String = "",
    val venueId: String = "",
    val venueName: String = "",
    val venueState: String = "",
    val venueCountry: String = "",
)