package com.entain.data.mapper

import com.entain.data.remote.model.NextRaceDto
import com.entain.domain.model.NextRace

fun NextRaceDto.toNextRace(): NextRace {
    return NextRace(
        raceId = raceId ?: "",
        raceName = raceName ?: "",
        raceNumber = raceNumber ?: 0,
        raceTime = advertisedStart.seconds ?: 0L,
        meetingId = meetingId ?: "",
        meetingName = meetingName ?: "",
        categoryId = categoryId ?: "",
    )
}

fun List<NextRaceDto>.toNextRaceList() = map(NextRaceDto::toNextRace)