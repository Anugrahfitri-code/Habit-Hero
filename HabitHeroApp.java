import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class HabitHeroApp {
    private JFrame frame;
    private DefaultListModel<String> habitListModel;
    private JList<String> habitList;
    private List<Habit> habits;
    private JLabel totalSkorLabel;
    private JLabel feedbackLabel;
    private JTextArea detailHabit;
    private boolean skorVisible = true;
    private String userName;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HabitHeroApp().showWelcomeScreen());
    }

    private void showWelcomeScreen() {
        JFrame welcomeFrame = new JFrame("Selamat Datang di HabitHero");
        welcomeFrame.setSize(400, 200);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(179, 229, 252));
        welcomeFrame.add(panel);
        placeWelcomeComponents(panel, welcomeFrame);

        welcomeFrame.setVisible(true);
    }

    private void placeWelcomeComponents(JPanel panel, JFrame welcomeFrame) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Masukkan Nama Anda:");
        welcomeLabel.setBounds(50, 30, 300, 25);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(welcomeLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(50, 60, 280, 25);
        panel.add(nameText);

        JButton startButton = new JButton("Mulai");
        startButton.setBounds(150, 100, 100, 25);
        panel.add(startButton);

        startButton.addActionListener(e -> {
            userName = nameText.getText().trim();
            if (!userName.isEmpty()) {
                welcomeFrame.dispose();
                initializeUI();
            } else {
                JOptionPane.showMessageDialog(panel, "Nama tidak boleh kosong.");
            }
        });
    }

    private void initializeUI() {
        frame = new JFrame("HabitHero – Swing Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        habits = new ArrayList<>();

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(144, 202, 249));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField habitNameField = new JTextField(15);
        JRadioButton positifBtn = new JRadioButton("Positif");
        JRadioButton negatifBtn = new JRadioButton("Negatif");
        JButton addButton = new JButton("Tambah Habit");

        ButtonGroup group = new ButtonGroup();
        group.add(positifBtn);
        group.add(negatifBtn);

        topPanel.add(new JLabel("Nama Habit:"));
        topPanel.add(habitNameField);
        topPanel.add(positifBtn);
        topPanel.add(negatifBtn);
        topPanel.add(addButton);

        JLabel greetingLabel = new JLabel("Selamat datang, " + userName + " di aplikasi HabitHero!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        greetingLabel.setForeground(new Color(13, 71, 161));
        topPanel.add(greetingLabel);

        JPanel skorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        skorPanel.setBackground(new Color(179, 229, 252));
        totalSkorLabel = new JLabel("Total Skor: 0");
        JButton toggleSkorBtn = new JButton("Sembunyikan Skor");

        feedbackLabel = new JLabel("Pengguna diharapkan agar sering melakukan kegiatan positif.");
        skorPanel.add(totalSkorLabel);
        skorPanel.add(toggleSkorBtn);
        skorPanel.add(feedbackLabel);

        toggleSkorBtn.addActionListener(e -> {
            skorVisible = !skorVisible;
            totalSkorLabel.setVisible(skorVisible);
            feedbackLabel.setVisible(skorVisible);
            toggleSkorBtn.setText(skorVisible ? "Sembunyikan Skor" : "Tampilkan Skor");
        });

        habitListModel = new DefaultListModel<>();
        habitList = new JList<>(habitListModel);
        habitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(habitList);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(179, 229, 252));
        centerPanel.add(listScrollPane, BorderLayout.CENTER);

        JPanel mainCenterPanel = new JPanel(new BorderLayout());
        mainCenterPanel.add(skorPanel, BorderLayout.NORTH);
        mainCenterPanel.add(centerPanel, BorderLayout.CENTER);

        detailHabit = new JTextArea(5, 20);
        detailHabit.setEditable(false);
        detailHabit.setBackground(new Color(225, 245, 254));

        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBackground(new Color(179, 229, 252));
        detailPanel.add(new JLabel("Detail Habit"), BorderLayout.NORTH);
        detailPanel.add(detailHabit, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(144, 202, 249));
        JButton selesaiBtn = new JButton("Tandai Selesai");
        JButton resetBtn = new JButton("Reset Skor");
        JButton hapusBtn = new JButton("Hapus Habit");

        bottomPanel.add(selesaiBtn);
        bottomPanel.add(resetBtn);
        bottomPanel.add(hapusBtn);

        addButton.addActionListener(e -> {
            String nama = habitNameField.getText().trim();
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nama habit tidak boleh kosong!");
                return;
            }

            Habit habit;
            if (positifBtn.isSelected()) {
                habit = new PositiveHabit(nama);
            } else if (negatifBtn.isSelected()) {
                habit = new NegativeHabit(nama);
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih tipe habit terlebih dahulu!");
                return;
            }

            habits.add(habit);
            habitListModel.addElement(habit.getDisplayName());
            habitNameField.setText("");
        });

        habitList.addListSelectionListener(e -> {
            int index = habitList.getSelectedIndex();
            if (index >= 0) {
                Habit selected = habits.get(index);
                detailHabit.setText("Nama: " + selected.getName() +
                        "\nTipe: " + (selected instanceof PositiveHabit ? "Positif" : "Negatif") +
                        "\nJumlah dilakukan: " + selected.getJumlahDilakukan() +
                        "\nSkor saat ini: " + selected.getScore());
            }
        });

        selesaiBtn.addActionListener(e -> {
            int index = habitList.getSelectedIndex();
            if (index >= 0) {
                Habit selected = habits.get(index);
                selected.markDone();
                habitListModel.set(index, selected.getDisplayName());
                updateTotalSkor();
            }
        });

        resetBtn.addActionListener(e -> {
            for (Habit h : habits) {
                h.resetScore();
            }
            refreshList();
            updateTotalSkor();
        });

        hapusBtn.addActionListener(e -> {
            int index = habitList.getSelectedIndex();
            if (index >= 0) {
                habits.remove(index);
                habitListModel.remove(index);
                detailHabit.setText("");
                updateTotalSkor();
            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainCenterPanel, BorderLayout.CENTER);
        frame.add(detailPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateTotalSkor() {
        int total = 0;
        for (Habit h : habits) {
            total += h.getScore();
        }
        totalSkorLabel.setText("Total Skor: " + total);

        String feedbackMessage;
        if (total < 30) {
            feedbackMessage = "Pengguna diharapkan agar sering melakukan kegiatan positif.";
        } else if (total < 45) {
            feedbackMessage = "Pengguna sudah lumayan melakukan kegiatan positif. Kurangi habit negatifnya.";
        } else {
            feedbackMessage = "Pengguna sudah konsisten melakukan kebiasaan baik.";
        }
        feedbackLabel.setText(feedbackMessage);
    }

    private void refreshList() {
        habitListModel.clear();
        for (Habit h : habits) {
            habitListModel.addElement(h.getDisplayName());
        }
    }
}
