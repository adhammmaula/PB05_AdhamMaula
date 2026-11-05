# Katalog Mata Kuliah — Refactor UDF, ViewModel, & StateFlow (Pertemuan 6)

Aplikasi Android "Katalog Mata Kuliah" yang dikembangkan menggunakan **Jetpack Compose** dan diperbarui dengan arsitektur **UI Layer + ViewModel + StateFlow + Unidirectional Data Flow (UDF)**.

## 👤 Identitas Mahasiswa
* **Nama**: Adham Maula Tirtasaputra
* **NIM**: 0102524001
* **Kelas**: IF24A
* **Program Studi**: Informatika 

---

## 🛠️ Tech Stack & Library Utama
* **Language**: Kotlin
* **UI Framework**: Jetpack Compose (Material3)
* **Architecture**: UI Layer + ViewModel + StateFlow + UDF
* **Navigation**: Navigation Compose (Type-Safe Route Serialization)
* **Lifecycle**: `androidx.lifecycle:lifecycle-runtime-compose` (`collectAsStateWithLifecycle`)
* **Minimum SDK**: API level 24+

---

## 🚀 Fitur Utama & Event UDF
1. **Pencarian Real-time**: Memfilter daftar mata kuliah berdasarkan kode atau nama matkul.
2. **Filter Kelas (Chip Filter)**: Menyaring daftar mata kuliah berdasarkan kelompok kelas (`IF24A`, `IF24H`, atau `ALL`).
3. **Reset Filter & Search**: Mengembalikan kondisi pencarian dan filter ke kondisi awal.
4. **Handling Empty State (Non-Happy Path)**: Menampilkan pesan error dan tombol reset secara otomatis ketika data hasil pencarian tidak ditemukan.
5. **State Preservation (Rotation Test)**: Input pencarian dan pilihan filter tetap konsisten dan tidak ter-reset saat layar dirotasikan dari *Portrait* ke *Landscape*.

---

## 🏗️ Struktur Proyek (Architecture Overview)
com.example.pb05_adhammaula/
├── Course.kt               # Model data
├── CourseRepository.kt     # Sumber data lokal (Repository)
├── CourseUiState.kt       # Immutable UI State
├── CourseViewModel.kt     # Screen-level State Holder (MutableStateFlow private)
├── HomeRoute.kt           # Lifecycle-aware State Collector
├── HomeScreen.kt          # Pure Stateless UI Component
├── DetailScreen.kt        # Detail Mata Kuliah Screen
├── FormScreen.kt          # Form Tambah/Edit Screen
├── Screen.kt              # Type-Safe Navigation Routes (@Serializable)
└── MainActivity.kt        # Entry point & NavHost setup

## 📋 Deklarasi AI Generatif
* **Penggunaan AI**: Ya
* **Tool yang Digunakan**: Gemini AI
* **Bagian yang Dibantu**: Brainstorming arsitektur UDF, penyusunan `CourseViewModel`, penataan `collectAsStateWithLifecycle()`, serta penanganan isu Git credentials.
* **Pemahaman & Perubahan**: Saya memahami pemisahan tanggung jawab antara ViewModel dan UI component, mengapa `MutableStateFlow` harus dibuat `private`, serta cara menjaga UI state tetap konsisten saat terjadi *configuration change* (rotasi layar).