package com.example.pb05_adhammaula

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    onCourseClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    viewModel: CourseViewModel = viewModel()
) {
    // Konsumsi state dengan lifecycle-aware collection
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChanged,
        onFilterChange = viewModel::onClassFilterChanged,
        onResetClick = viewModel::onResetFilter,
        onCourseClick = onCourseClick,
        onAddClick = onAddClick
    )
}