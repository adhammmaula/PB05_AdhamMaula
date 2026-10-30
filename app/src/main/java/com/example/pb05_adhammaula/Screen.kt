package com.example.pb05_adhammaula

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Home

    @Serializable
    data class Detail(val courseId: Int)

    @Serializable
    data class Form(val courseId: Int = -1) // -1 untuk mode Tambah Baru
}