# Katalog Mata Kuliah — Local Persistence Challenge (Room + DataStore) — Pertemuan 7

Aplikasi Android **Katalog Mata Kuliah** yang dikembangkan menggunakan **Jetpack Compose** dan diperbarui dengan arsitektur **Data Layer Modern (Room Database + Preferences DataStore + Repository + ViewModel + StateFlow)**.

## 👤 Identitas Mahasiswa
* **Nama**: Adham Maula Tirtasaputra
* **NIM**: 0102524001
* **Kelas**: IF24A
* **Program Studi**: Informatika (UAI)

---

## 🛠️ Tech Stack & Library Utama
* **Language**: Kotlin
* **UI Framework**: Jetpack Compose (Material 3)
* **Local Database**: Room Database (`androidx.room`) — Entity, DAO, RoomDatabase
* **Preferences**: DataStore Preferences (`androidx.datastore:datastore-preferences`)
* **Architecture**: Clean Data Layer (UI → ViewModel → Repository → Room / DataStore)
* **Asynchronous / Reactive**: Kotlin Coroutines & Flow (`StateFlow`, `collectAsStateWithLifecycle`)
* **Navigation**: Navigation Compose (Type-Safe Route Serialization)
* **Code Processing**: KSP (Kotlin Symbol Processing)
* **Minimum SDK**: API level 24+

---

## 🚀 Fitur Utama & Persistence
1. **Local Database (Room Database)**:
   - **Entity**: `CourseEntity` (Minimal 5 field: `id`, `code`, `name`, `sks`, `description`).
   - **DAO**: Read berbasis `Flow`, `insertCourse`, `updateCourse`, `deleteCourse`, `getCourseById`.
   - **Database**: `CourseDatabase` dengan callback `onCreate` untuk pre-populate data awal (8 mata kuliah).
   - **CRUD Complete**: Tambah, Edit, Hapus, dan Lihat Detail Mata Kuliah. Data tersimpan secara persisten di SQLite lokal.
2. **Preference Storage (DataStore Preferences)**:
   - **Dark Mode**: Menyimpan mode tampilan gelap/terang secara persisten.
   - **Filter Kelas**: Menyimpan pilihan filter kelas (`ALL`, `IF24A`, `IF24H`) secara persisten.
3. **Pencarian Real-time & Filter**: Memfilter daftar mata kuliah secara dinamis.
4. **State Preservation & Persistence Test**: Data Room dan preferensi DataStore tetap bertahan 100% setelah aplikasi ditutup (*close*) dan dibuka kembali (*reopen*).

---

## 📐 Arsitektur Data Layer (Data Flow Diagram)

```
       [ Composable UI ] (HomeScreen, DetailScreen, FormScreen)
              │  ▲
   User Event │  │ UiState (StateFlow)
              ▼  │
      [ CourseViewModel ]
              │  ▲
  Coroutines  │  │ Flow<List<CourseEntity>>, Flow<Boolean>, Flow<String>
              ▼  │
     [ CourseRepository ]
          ┌───┴────────────────┐
          ▼                    ▼
   [ CourseDao ]     [ UserPreferencesRepository ]
   (Room SQLite)       (DataStore Preferences)
```

---

## 🏗️ Struktur Proyek (Project Structure)
```
com.example.pb05_adhammaula/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   │   └── CourseDao.kt                   # Data Access Object (Query & Flow)
│   │   ├── database/
│   │   │   └── CourseDatabase.kt              # Room Database & Pre-populate Seed Data
│   │   ├── entity/
│   │   │   └── CourseEntity.kt                # Room Entity (id, code, name, sks, description)
│   │   └── preferences/
│   │       └── UserPreferencesRepository.kt   # Preferences DataStore (Dark Mode & Filter)
│   └── repository/
│       └── CourseRepository.kt                # Single Entry Point Data Layer
├── CourseApplication.kt                       # Application class untuk DB & Repository Provider
├── CourseUiState.kt                           # Immutable UI State
├── CourseViewModel.kt                         # State Holder & ViewModelFactory
├── HomeRoute.kt                               # Lifecycle-aware State Collector Component
├── HomeScreen.kt                              # Composable Home Page with Dark Mode Switch
├── DetailScreen.kt                            # Composable Detail Page with Edit & Delete Action
├── FormScreen.kt                              # Composable Form Page (Add & Edit Course)
├── Screen.kt                                  # Type-Safe Navigation Routes (@Serializable)
└── MainActivity.kt                            # Entry Point App & Theme Provider
```

---

## 📋 Deklarasi AI Generatif
* **Penggunaan AI**: Ya
* **Tool yang Digunakan**: Gemini AI (Android Studio Plugin)
* **Bagian yang Dibantu**: Integrasi Room Database dengan KSP, konfigurasi DataStore Preferences `preferencesDataStore`, pembuatan `CourseRepository` sebagai single source of truth, serta penggabungan multiple Flow (`combine`) pada `CourseViewModel`.
* **Pemahaman & Perubahan**: Saya memahami sepenuhnya alur data reaktif menggunakan `Flow` dari Room dan DataStore ke Repository, bagaimana `CourseViewModel` memproses state tersebut secara terpusat tanpa Composable mengakses DAO secara langsung, serta bagaimana data bertahan setelah restart aplikasi.
