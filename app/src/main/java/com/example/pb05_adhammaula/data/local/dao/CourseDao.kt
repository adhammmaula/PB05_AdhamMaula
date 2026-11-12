package com.example.pb05_adhammaula.data.local.dao

import androidx.room.*
import com.example.pb05_adhammaula.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses ORDER BY id ASC")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE id = :id")
    fun getCourseById(id: Int): Flow<CourseEntity?>

    @Query("SELECT * FROM courses WHERE id = :id")
    suspend fun getCourseByIdDirect(id: Int): CourseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Delete
    suspend fun deleteCourse(course: CourseEntity)

    @Query("DELETE FROM courses WHERE id = :id")
    suspend fun deleteCourseById(id: Int)
}
