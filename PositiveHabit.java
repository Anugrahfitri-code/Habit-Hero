public class PositiveHabit extends Habit {
    public void markDone() {
        System.out.println("Habit adalah positif, skor bertambah.");
        this.increaseScore();
    }
}
