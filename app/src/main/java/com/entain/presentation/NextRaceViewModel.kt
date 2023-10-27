package com.entain.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entain.core.Resource
import com.entain.core.helper.Constant.MAX_ITEM_VIEW
import com.entain.domain.model.NextRace
import com.entain.domain.usecase.AddCategoryUseCase
import com.entain.domain.usecase.GetCategoryUseCase
import com.entain.domain.usecase.GetNextRaceUseCase
import com.entain.domain.usecase.GetSelectedCategoryUseCase
import com.entain.domain.usecase.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NextRaceViewModel @Inject constructor(
    private val getNextRaceUseCase: GetNextRaceUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getSelectedCategoryUseCase: GetSelectedCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NextRaceState())
    val uiState: StateFlow<NextRaceState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            addCategoryUseCase()
            getNextRaceList()
            getCategories()
            getSelectedIdList()
        }
    }

    private fun getNextRaceList() {
        viewModelScope.launch {
            getNextRaceUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { currentUiState ->
                            currentUiState.copy(
                                nextRaceList = result.data ?: emptyList(),
                                nextRaceFilteredList = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update { currentUiState ->
                            currentUiState.copy(
                                nextRaceList = result.data ?: emptyList(),
                                nextRaceFilteredList = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        updateSelectedCategory()
                    }

                    is Resource.Error -> {
                        _uiState.update { currentUiState ->
                            currentUiState.copy(
                                nextRaceList = result.data ?: emptyList(),
                                nextRaceFilteredList = result.data ?: emptyList(),
                                isLoading = false,
                                errorMessage = result.message ?: "Something went wrong"
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getCategories(){
        viewModelScope.launch {
            getCategoryUseCase().onEach { result ->
                _uiState.update { currentState ->
                    currentState.copy(
                        categoryModelFilterList = result
                    )
                }
            }.launchIn(this)
        }
    }
    private fun getSelectedIdList() {
        viewModelScope.launch {
            getSelectedCategoryUseCase().onEach { result ->
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedCategoryIdList = result
                    )
                }
            }.launchIn(this)
        }
    }

    private fun restoreMainList() {
        _uiState.update { state ->
            state.copy(
                nextRaceList = _uiState.value.nextRaceFilteredList
            )
        }
    }

    fun onUpdateValue(string: String, isChecked: Boolean) {
        viewModelScope.launch {
            updateCategoryUseCase(string, isChecked)
            restoreMainList()
            _uiState.update { currentState ->
                currentState.copy(
                    selectedCategoryIdList =
                    if(isChecked)
                        _uiState.value.selectedCategoryIdList.plus(string)
                    else
                        _uiState.value.selectedCategoryIdList.minus(string),
                )
            }
            updateSelectedCategory()
        }
    }

    private fun updateSelectedCategory() {
        _uiState.update { currentState ->
            currentState.copy(
                nextRaceList = if (_uiState.value.selectedCategoryIdList.isNotEmpty()) {
                    _uiState.value.nextRaceList.filter {
                        _uiState.value.selectedCategoryIdList.contains(it.categoryId)
                    }
                } else {
                    _uiState.value.nextRaceList
                },
            )
        }
    }

    fun removeItem(nextRace: NextRace) {
        _uiState.update { currentState ->
            currentState.copy(
                nextRaceList = _uiState.value.nextRaceList.minus(nextRace),
                nextRaceFilteredList = _uiState.value.nextRaceFilteredList.minus(nextRace),
            )
        }
        if(_uiState.value.nextRaceList.size < MAX_ITEM_VIEW){
            getNextRaceList()
        }
    }
}