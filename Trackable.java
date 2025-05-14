public class Trackable extends Habit {
    @Override
    public void markDone() {
        System.out.println("Habit adalah positif, skor bertambah.");
        this.increaseScore();
    }

    @Override
    public void resetScore() {
        System.out.println("Habit adalah positif, skor direset.");
        this.setScore(0);
    }
    
}