package com.entain.presentation

import com.entain.domain.model.CategoryModel
import com.entain.domain.model.NextRace

data class NextRaceState(
    val nextRaceList: List<NextRace> = arrayListOf(),
    val nextRaceFilteredList: List<NextRace> = arrayListOf(),
    val categoryModelFilterList: List<CategoryModel> = emptyList(),
    val selectedCategoryIdList: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)