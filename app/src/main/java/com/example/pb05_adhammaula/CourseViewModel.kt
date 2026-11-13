package com.example.pb05_adhammaula

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pb05_adhammaula.data.local.entity.CourseEntity
import com.example.pb05_adhammaula.data.repository.CourseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CourseViewModel(
    private val repository: CourseRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<CourseUiState> = combine(
        repository.allCourses,
        repository.classFilter,
        repository.isDarkMode,
        _searchQuery
    ) { courses, classFilter, isDarkMode, searchQuery ->
        CourseUiState(
            courses = courses,
            searchQuery = searchQuery,
            selectedClassFilter = classFilter,
            isDarkMode = isDarkMode
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CourseUiState()
    )

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onClassFilterChanged(filter: String) {
        viewModelScope.launch {
            repository.setClassFilter(filter)
        }
    }

    fun onDarkModeChanged(enabled: Boolean) {
        viewModelScope.launch {
            repository.setDarkMode(enabled)
        }
    }

    fun onResetFilter() {
        _searchQuery.value = ""
        viewModelScope.launch {
            repository.setClassFilter("ALL")
        }
    }

    fun saveCourse(id: Int?, code: String, name: String, sks: Int, description: String) {
        viewModelScope.launch {
            val course = CourseEntity(
                id = if (id != null && id > 0) id else 0,
                code = code,
                name = name,
                sks = sks,
                description = description
            )
            if (id == null || id <= 0) {
                repository.insertCourse(course)
            } else {
                repository.updateCourse(course)
            }
        }
    }

    fun deleteCourse(course: CourseEntity) {
        viewModelScope.launch {
            repository.deleteCourse(course)
        }
    }

    fun deleteCourseById(id: Int) {
        viewModelScope.launch {
            repository.deleteCourseById(id)
        }
    }

    fun getCourseById(id: Int): Flow<CourseEntity?> {
        return repository.getCourseById(id)
    }
}

class CourseViewModelFactory(
    private val repository: CourseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
