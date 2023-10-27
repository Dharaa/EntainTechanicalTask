package com.entain.data.datasource

import com.entain.data.remote.model.AdvertisedStart
import com.entain.data.remote.model.Data
import com.entain.data.remote.model.NextRaceDto
import com.entain.data.remote.model.NextRaceResponse
import com.entain.domain.model.CategoryModel

object FakeDataSource {

    var nextRaceResponse = NextRaceResponse(
        data = Data(
            raceSummaries = getRaceSummaries()
        ),
        message = "Next 5 races from each category",
        status = 200
    )

    var localCategoryList = getCategoryList()
    var selectedCategories = getSelectedCategory()
}

fun getRaceSummaries() : Map<String, NextRaceDto>{
    val raceSummaries: HashMap<String, NextRaceDto> = HashMap()
    raceSummaries["020dc609-1dc3-447a-945c-8818d31c3f16"] = NextRaceDto(
        raceId = "020dc609-1dc3-447a-945c-8818d31c3f16",
        raceName = "Bet In Play On Rhino.Bet Handicap Hurdle",
        raceNumber = 4,
        meetingId = "58988f57-be6a-4901-a66e-0125096bb6ab",
        meetingName = "Carlisle",
        categoryId = "4a2788f8-e825-4d36-9894-efd4baf1cfae",
        advertisedStart =
        AdvertisedStart(
            seconds = 1698330000
        )
    )
    return raceSummaries
}

fun getCategoryList(): List<CategoryModel> {
    val items: MutableList<CategoryModel> = ArrayList()
    items.add(CategoryModel("HORSE", "4a2788f8-e825-4d36-9894-efd4baf1cfae", false))
    items.add(CategoryModel("HARNESS", "161d9be2-e909-4326-8c2c-35ed71fb460b", false))
    items.add(CategoryModel("GREYHOUND", "9daef0d7-bf3c-4f50-921d-8e818c60fe61", false))
    return items
}

fun getSingleCategory() : CategoryModel{
    return CategoryModel("GREYHOUND", "9daef0d7-bf3c-4f50-921d-8e818c60fe61", false)
}

fun getSelectedCategory() : List<String>{
    return listOf("4a2788f8-e825-4d36-9894-efd4baf1cfae", "161d9be2-e909-4326-8c2c-35ed71fb460b")
}