# Katalog Mata Kuliah - Multi-Screen Navigation

Aplikasi Android katalog mata kuliah berbasis Jetpack Compose dan Material 3 untuk memenuhi Tugas Pertemuan 5 Pemrograman Bergerak.

## Identitas
- **Nama**: Adham Maula Tirtasaputra
- **NIM**: 0102524001
- **Kelas**: IF24A
- **Tema**: Katalog Mata Kuliah (Digit NIM 0–1)

## Fitur Utama
1. **Home Screen**: Menampilkan daftar minimal 8 mata kuliah menggunakan `LazyColumn`.
2. **Detail Screen**: Menampilkan informasi lengkap mata kuliah berdasarkan ID, serta menangani kondisi *invalid ID*.
3. **Form Add/Edit Screen**: Form untuk menambah/mengubah data mata kuliah lengkap dengan validasi input (Kode & Nama tidak boleh kosong).
4. **Type-Safe Navigation**: Menggunakan `kotlinx-serialization` untuk perpindahan antar screen.

## Navigation Destinations & Routes
- `Screen.Home` (`HomeScreen`)
- `Screen.Detail(courseId: Int)` (`DetailScreen`)
- `Screen.Form(courseId: Int = -1)` (`FormScreen`)

## Cara Menjalankan
1. Clone repository ini: `git clone <https://github.com/adhammmaula/PB05_AdhamMaula>`
2. Buka project di Android Studio (Ladybug / Iguana ke atas).
3. Jalankan Gradle Sync.
4. Run project pada Emulator (Pixel 7 / Android 10+).

## Deklarasi AI
Saya menggunakan AI generatif sebagai alat bantu dalam memahami contoh API Navigation Compose type-safe, pembuatan struktur layout Compose, dan refactoring validasi form.