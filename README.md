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

## 💡 Skema Index Skor

- **Habit Positif**
  - Skor bertambah secara progresif: +10, +15, +20, dst.
- **Habit Negatif**
  - Skor menurun secara progresif: -5, -15, -30, dst.

---
## Struktur Class

### Interface dan Abstract
- **Trackable** (interface):
  - `markDone()`, `resetScore()`
- **Habit** (abstract class):
  - Attribute: `name`, `skor`
  - Method: `getName()`, `getScore()`, `increaseScore()`, `decreaseScore()`, `resetScore()`, `markDone()` (abstract)

### Implementasi
- **PositiveHabit**: extends `Habit`, skor akan bertambah saat ditandai selesai
- **NegativeHabit**: extends `Habit`, skor akan berkurang saat ditandai selesai
- **HabitManajer**:
  - Menyimpan list habit (`List<Trackable>`)
  - Method: `addHabit()`, `resetScore()`, `markHabit()`, `removeHabit()`, `getHabits()`
- **HabitApp**:
  - Versi CLI dari aplikasi, berbasis `Scanner`
- **HabitHeroSwingGUI**:
  - GUI Swing utama dengan daftar habit, tombol aksi, dan total skor

---

## Cara Menjalankan Program

### Versi CLI
1. Compile:
```bash
javac *.java
```
2. Jalankan:
```bash
java HabitApp
```

### Versi GUI (Swing)
1. Compile:
```bash
javac *.java
```
2. Jalankan:
```bash
java HabitHeroSwingGUI
```

Tidak perlu module-path seperti JavaFX, cukup JDK biasa.

---

## Pembagian Tugas Anggota

| Nama Anggota                         | Tugas                                                                 |
|--------------------------------------|-----------------------------------------------------------------------|
| **Anugrah Fitri Novanda**            | Membuat `HabitApp.java` dan `HabitHeroSwingGUI.java` (GUI)            |
| **Vina Sucitra**                     | Membuat class `Habit.java`                                            |
| **Muhammad Alif Sakti**             | Membuat interface `Trackable`, class `PositiveHabit`, `NegativeHabit` |
| **Andi Sophie Banuna Amrie**         | Membuat class `HabitManajer.java`                                     |

---



