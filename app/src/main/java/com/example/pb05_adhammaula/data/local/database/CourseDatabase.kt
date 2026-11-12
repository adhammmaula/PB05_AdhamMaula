package com.example.pb05_adhammaula.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pb05_adhammaula.data.local.dao.CourseDao
import com.example.pb05_adhammaula.data.local.entity.CourseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: CourseDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CourseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CourseDatabase::class.java,
                    "course_database"
                )
                    .addCallback(CourseDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class CourseDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.courseDao())
                    }
                }
            }

            suspend fun populateDatabase(courseDao: CourseDao) {
                val initialCourses = listOf(
                    CourseEntity(1, "IF24A", "Pemrograman Berbasis Objek", 3, "Konsep OOP: inheritance, polymorphism, encapsulation."),
                    CourseEntity(2, "IF24A", "Struktur Data & Algoritma", 3, "Array, list, tree, graph, serta efisiensi algoritma."),
                    CourseEntity(3, "IF24A", "Pemrograman Bergerak", 3, "Pengembangan aplikasi Android dengan Kotlin & Jetpack Compose."),
                    CourseEntity(4, "IF24A", "Basis Data", 3, "Perancangan RDBMS, normalisasi data, dan query SQL."),
                    CourseEntity(5, "IF24H", "Sistem Operasi", 3, "Manajemen proses, memori, I/O, dan file system."),
                    CourseEntity(6, "IF24H", "Jaringan Komputer", 3, "Arsitektur TCP/IP, OSI Model, routing, dan switching."),
                    CourseEntity(7, "IF24H", "Kecerdasan Buatan", 3, "Konsep Machine Learning, Neural Networks, dan Search Algorithms."),
                    CourseEntity(8, "IF24H", "Teknopreneurship", 2, "Pengembangan produk teknologi dan perancangan model bisnis.")
                )
                courseDao.insertAll(initialCourses)
            }
        }
    }
}
