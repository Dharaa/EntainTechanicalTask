package com.entain.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.entain.R
import com.entain.presentation.components.NextRaceListItem

@Composable
fun NextRaceListScreen(
    viewModel: NextRaceViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = colorResource(R.color.toolbar)
                ),
        ) {
            Text(
                modifier = Modifier.padding(2.dp),
                color = Color.White,
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.value.nextRaceList.isNotEmpty()) {
                state.value.categoryModelFilterList.forEach { category ->
                    Checkbox(
                        checked = category.isSelected,
                        onCheckedChange = { isChecked ->
                            viewModel.onUpdateValue(category.categoryId, isChecked)
                        },
                        colors = CheckboxDefaults.colors(Color.Blue)
                    )
                    Text(
                        text = category.categoryName,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.value.nextRaceList.take(5).size) { pos ->
                    NextRaceListItem(
                        nextRaceItem = state.value.nextRaceList[pos],
                        onItemClick = {
                            viewModel.removeItem(it)
                        }
                    )
                }
            }
            if (state.value.errorMessage.isNotBlank()) {
                Text(
                    text = state.value.errorMessage,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (state.value.nextRaceList.isEmpty() && state.value.errorMessage.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.empty_list_message),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

