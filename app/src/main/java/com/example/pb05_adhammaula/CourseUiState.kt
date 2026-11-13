package com.example.pb05_adhammaula

import com.example.pb05_adhammaula.data.local.entity.CourseEntity

data class CourseUiState(
    val courses: List<CourseEntity> = emptyList(),
    val searchQuery: String = "",
    val selectedClassFilter: String = "ALL", // "ALL", "IF24A", "IF24H"
    val isDarkMode: Boolean = false
)
