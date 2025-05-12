import java.util.Scanner;

public class HabitApp {
    private HabitManajer manajer;
    private Scanner scanner;

    public HabitApp() {
        manajer = new HabitManajer();
        scanner = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        System.out.println("=== Habit Hero ===");
        System.out.println("1. Tambah Habit");
        System.out.println("2. Lihat Semua Habit");
        System.out.println("3. Tandai Habit Sudah Dilakukan");
        System.out.println("4. Reset Skor Habit");
        System.out.println("5. Lihat Hasil Habit");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu: ");
    }

    public void tanganiInputPengguna() {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            try {
                int pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Nama habit: ");
                        String nama = scanner.nextLine();

                        if (manajer.isHabitExists(nama)) {
                            System.out.println("Habit yang anda masukkan sama.");
                            break;
                        }

                        String jenis = "";
                        while (true) {
                            System.out.print("Apakah ini habit positif? (y/n): ");
                            jenis = scanner.nextLine().trim();
                            if (jenis.equalsIgnoreCase("y") || jenis.equalsIgnoreCase("n")) {
                                break;
                            } else {
                                System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
                            }
                        }

                        if (jenis.equalsIgnoreCase("y")) {
                            manajer.addHabit(new PositiveHabit(nama));
                        } else {
                            manajer.addHabit(new NegativeHabit(nama));
                        }
                        break;

                    case 2:
                        int index = 1;
                        for (Trackable h : manajer.getAllHabits()) {
                            Habit habit = (Habit) h;
                            System.out.println(index + ". " + habit.getName() + " | Skor: " + habit.getScore());
                            index++;
                        }
                        System.out.println("Total Skor: " + manajer.getTotalScore());

                        String hapus = "";
                        while (true) {
                            System.out.print("Apakah Anda ingin menghapus habit? (y/n): ");
                            hapus = scanner.nextLine();
                            if (hapus.equalsIgnoreCase("y") || hapus.equalsIgnoreCase("n")) {
                                break;
                            } else {
                                System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
                            }
                        }
                        if (hapus.equalsIgnoreCase("y")) {
                            while (true) {
                                System.out.print("Pilih index habit yang ingin dihapus: ");
                                try {
                                    int hapusIdx = Integer.parseInt(scanner.nextLine());
                                    if (hapusIdx < 1 || hapusIdx > manajer.getAllHabits().size()) {
                                        System.out.println("Index tidak valid. Coba lagi.");
                                        continue;
                                    }
                                    manajer.removeHabit(hapusIdx - 1);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
                                }
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Daftar habit yang sudah ada:");
                        index = 1;
                        for (Trackable h : manajer.getAllHabits()) {
                            Habit habit = (Habit) h;
                            System.out.println(index + ". " + habit.getName());
                            index++;
                        }
                        while (true) {
                            System.out.print("Pilih index habit yang sudah dilakukan: ");
                            try {
                                int idxMark = Integer.parseInt(scanner.nextLine());
                                if (idxMark < 1 || idxMark > manajer.getAllHabits().size()) {
                                    System.out.println("Index tidak valid. Coba lagi.");
                                    continue;
                                }
                                manajer.markHabit(idxMark - 1);
                                break;
                            } catch (Exception e) {
                                System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Daftar habit yang sudah ada:");
                        index = 1;
                        for (Trackable h : manajer.getAllHabits()) {
                            Habit habit = (Habit) h;
                            System.out.println(index + ". " + habit.getName());
                            index++;
                        }

                        String reset = "";
                        while (true) {
                            System.out.print("Apakah Anda ingin mereset skor habit? (y/n): ");
                            reset = scanner.nextLine().trim();
                            if (reset.equalsIgnoreCase("y") || reset.equalsIgnoreCase("n")) {
                                break;
                            } else {
                                System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
                            }
                        }

                        if (reset.equalsIgnoreCase("y")) {
                            while (true) {
                                System.out.print("Pilih index habit untuk reset: ");
                                try {
                                    int idxReset = Integer.parseInt(scanner.nextLine());
                                    if (idxReset < 1 || idxReset > manajer.getAllHabits().size()) {
                                        System.out.println("Index tidak valid. Coba lagi.");
                                        continue;
                                    }
                                    manajer.resetScore(idxReset - 1);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
                                }
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Hasil Habit yang telah dilakukan:");
                        int totalSkor = 0;
                        for (Trackable h : manajer.getAllHabits()) {
                            Habit habit = (Habit) h;
                            totalSkor += habit.getScore();
                            System.out.println(habit.getName() + " | Skor: " + habit.getScore());
                        }
                        System.out.println("Total Skor: " + totalSkor);

                        if (totalSkor < 30) {
                            System.out.println("Pengguna diharapkan agar sering melakukan kegiatan positif, hindari melakukan kebiasaan negatif terlalu sering.");
                        } else if (totalSkor <= 40) {
                            System.out.println("Pengguna sudah lumayan melakukan kegiatan positif namun masih ada kebiasaan negatifnya yang perlu dikurangi.");
                        } else {
                            System.out.println("Pengguna sudah konsisten melakukan kebiasaan baik.");
                        }
                        break;

                    case 6:
                        berjalan = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }

            } catch (Exception e) {
                System.out.println("Input yang anda masukkan salah, mohon input ulang dengan benar.");
            }
        }
    }

    public void jalankan() {
        tanganiInputPengguna();
    }

    public static void main(String[] args) {
        HabitApp app = new HabitApp();
        app.jalankan();
    }
}
