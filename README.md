# Habit-Hero
package ini dibuat oleh kelompok 7, sebuah aplikasi _habit tracker_ berbasis Java yang membantu pengguna membentuk kebiasaan baik dan mengurangi kebiasaan buruk melalui sistem penilaian (index skor) yang sederhana namun efektif. Kami juga menambahkan GUI sederhana di dalam projek ini

---

## 🎯 Fitur Utama

- ✅ Tambah habit positif atau negatif
- ✅ Tandai habit yang sudah dilakukan
- ✅ Lihat dan hapus habit
- ✅ Reset skor habit 
- ✅ Perhitungan index skor kumulatif berdasarkan seberapa sering dilakukan
- ✅ Validasi input (y/n & angka) dengan sistem try-catch

---
## Struktur Class

### Interface dan Abstract
- **Trackable** (interface):
  - `markDone()`, `resetScore()`
- **Habit** (abstract class):
  - Atribut: `name`, `jumlahDilakukan`
  - Method: `getName()`, `getScore()` (abstract override), `getJumlahDilakukan()`, `resetScore()`, `markDone()` (abstract)

### Implementasi Class
- **PositiveHabit**: turunan `Habit`, skor meningkat progresif (10, 15, 20,...)
- **NegativeHabit**: turunan `Habit`, skor menurun progresif (-5, -10, -15,...)
- **HabitManajer**:
  - Menyimpan daftar kebiasaan (List<Trackable>)
  - Method: `addHabit()`, `markHabit()`, `resetScore()`, `removeHabit()`, `isHabitExists()`, `getHabits()`, `getAllHabits()`, `getTotalScore()`
- **HabitApp**:
  - Versi CLI dengan input/output menggunakan `Scanner`
  - Menu: tambah, lihat, tandai selesai, reset, dan hasil habit
- **HabitHeroSwingGUI**:
  - Versi GUI Swing dengan list kebiasaan, detail info, dan tombol aksi
  - Menyediakan feedback berdasarkan total skor

---

## Cara Menjalankan Program

### 1. Versi CLI (HabitApp)
```bash
javac *.java
java HabitApp
```

### 2. Versi GUI Swing (HabitHeroSwingGUI)
```bash
javac *.java
java HabitHeroSwingGUI
```

Tidak membutuhkan JavaFX, cukup JDK standar (versi 8+).

---

## Pembagian Tugas Anggota

| Nama Anggota                   | Tugas                                                                 |
|-------------------------------|-----------------------------------------------------------------------|
| **Anugrah Fitri Novanda**     | Membuat `HabitApp` dan `HabitHeroSwingGUI` (GUI Swing)                |
| **Vina Sucitra**              | Membuat `Habit.java` (kelas abstrak)            |
| **Muhammad Alif Sakti**       | Membuat `Trackable`, `PositiveHabit`, dan `NegativeHabit`             |
| **Andi Sophie Banuna Amrie**  | Membuat `HabitManajer.java`    |

---



