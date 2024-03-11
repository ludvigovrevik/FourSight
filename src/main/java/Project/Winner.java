package project;

public class Winner {
    private String name;
    private int wins;

    public Winner(String name) {
        this.wins = 1;
        this.name = name;
    }

    public Winner(String name, int wins) {
        this.wins = wins;
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
