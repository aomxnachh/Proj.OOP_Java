package main.model;

public class Score {
    private int wins;
    private int losses;
    private int draws;

    public Score() {
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    public void addWin() { wins++; }
    public void addLoss() { losses++; }
    public void addDraw() { draws++; }

    // Getters
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getDraws() { return draws; }
}