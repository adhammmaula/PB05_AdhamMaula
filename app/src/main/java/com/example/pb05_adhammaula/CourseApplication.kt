package com.example.pb05_adhammaula

import android.app.Application
import com.example.pb05_adhammaula.data.local.database.CourseDatabase
import com.example.pb05_adhammaula.data.local.preferences.UserPreferencesRepository
import com.example.pb05_adhammaula.data.repository.CourseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CourseApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { CourseDatabase.getDatabase(this, applicationScope) }
    val userPreferencesRepository by lazy { UserPreferencesRepository(this) }
    val repository by lazy { CourseRepository(database.courseDao(), userPreferencesRepository) }
}
