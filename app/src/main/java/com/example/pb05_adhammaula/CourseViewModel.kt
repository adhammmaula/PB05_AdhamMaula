package com.example.pb05_adhammaula

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseViewModel : ViewModel() {

    // MutableStateFlow bersifat private sesuai ketentuan
    private val _uiState = MutableStateFlow(CourseUiState())
    // Expose read-only StateFlow
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        _uiState.update {
            it.copy(courses = CourseRepository.courses)
        }
    }

    // Event 1: Change Search Query
    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }
    }

    // Event 2: Change Class Filter
    fun onClassFilterChanged(filter: String) {
        _uiState.update { it.copy(selectedClassFilter = filter) }
    }

    // Event 3: Reset Filter
    fun onResetFilter() {
        _uiState.update {
            it.copy(searchQuery = "", selectedClassFilter = "ALL")
        }
    }
}