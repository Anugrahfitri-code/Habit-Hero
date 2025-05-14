public class NegativeHabit extends Habit {
    public void markDone() {
        System.out.println("Habit adalah negatif, skor berkurang.");
        this.decreaseScore();
    }
}
