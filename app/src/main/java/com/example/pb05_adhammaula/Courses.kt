package com.example.pb05_adhammaula

import androidx.compose.runtime.mutableStateListOf

data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val sks: Int,
    val description: String
)

object CourseRepository {
    private var currentId = 9

    // Minimal 8 item dengan kode kelas IF24A dan IF24H saja
    val courses = mutableStateListOf(
        Course(1, "IF24A", "Pemrograman Berbasis Objek", 3, "Konsep OOP: inheritance, polymorphism, encapsulation."),
        Course(2, "IF24A", "Struktur Data & Algoritma", 3, "Array, list, tree, graph, serta efisiensi algoritma."),
        Course(3, "IF24A", "Pemrograman Bergerak", 3, "Pengembangan aplikasi Android dengan Kotlin & Jetpack Compose."),
        Course(4, "IF24A", "Basis Data", 3, "Perancangan RDBMS, normalisasi data, dan query SQL."),
        Course(5, "IF24H", "Sistem Operasi", 3, "Manajemen proses, memori, I/O, dan file system."),
        Course(6, "IF24H", "Jaringan Komputer", 3, "Arsitektur TCP/IP, OSI Model, routing, dan switching."),
        Course(7, "IF24H", "Kecerdasan Buatan", 3, "Konsep Machine Learning, Neural Networks, dan Search Algorithms."),
        Course(8, "IF24H", "Teknopreneurship", 2, "Pengembangan produk teknologi dan perancangan model bisnis.")
    )

    fun getCourseById(id: Int): Course? = courses.find { it.id == id }

    fun addOrUpdateCourse(id: Int?, code: String, name: String, sks: Int, description: String) {
        if (id == null || id == -1) {
            courses.add(Course(currentId++, code, name, sks, description))
        } else {
            val index = courses.indexOfFirst { it.id == id }
            if (index != -1) {
                courses[index] = Course(id, code, name, sks, description)
            }
        }
    }
}