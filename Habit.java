public abstract class Habit implements Trackable {
    protected String name;
    protected int jumlahDilakukan;

    public Habit(String name) {
        this.name = name;
        this.jumlahDilakukan = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return 0;  
    }

    public int getJumlahDilakukan() {
        return jumlahDilakukan;
    }

    public void resetScore() {
        this.jumlahDilakukan = 0;
    }

    public abstract void markDone();
}
