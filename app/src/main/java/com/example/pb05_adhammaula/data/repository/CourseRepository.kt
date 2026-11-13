package com.example.pb05_adhammaula.data.repository

import com.example.pb05_adhammaula.data.local.dao.CourseDao
import com.example.pb05_adhammaula.data.local.entity.CourseEntity
import com.example.pb05_adhammaula.data.local.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class CourseRepository(
    private val courseDao: CourseDao,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    val allCourses: Flow<List<CourseEntity>> = courseDao.getAllCourses()

    fun getCourseById(id: Int): Flow<CourseEntity?> = courseDao.getCourseById(id)

    suspend fun getCourseByIdDirect(id: Int): CourseEntity? = courseDao.getCourseByIdDirect(id)

    suspend fun insertCourse(course: CourseEntity) {
        courseDao.insertCourse(course)
    }

    suspend fun updateCourse(course: CourseEntity) {
        courseDao.updateCourse(course)
    }

    suspend fun deleteCourse(course: CourseEntity) {
        courseDao.deleteCourse(course)
    }

    suspend fun deleteCourseById(id: Int) {
        courseDao.deleteCourseById(id)
    }

    val isDarkMode: Flow<Boolean> = userPreferencesRepository.isDarkMode

    val classFilter: Flow<String> = userPreferencesRepository.classFilter

    suspend fun setDarkMode(enabled: Boolean) {
        userPreferencesRepository.setDarkMode(enabled)
    }

    suspend fun setClassFilter(filter: String) {
        userPreferencesRepository.setClassFilter(filter)
    }
}
