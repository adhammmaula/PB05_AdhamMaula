package com.example.pb05_adhammaula

data class CourseUiState(
    val courses: List<Course> = emptyList(),
    val searchQuery: String = "",
    val selectedClassFilter: String = "ALL" // "ALL", "IF24A", "IF24H"
)