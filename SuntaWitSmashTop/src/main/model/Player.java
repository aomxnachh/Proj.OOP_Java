package main.model;

import main.enums.Choice;
import main.util.Constants;

public class Player {
    private String name;
    private int health;
    private Choice currentChoice;
    private boolean hasChosen;
    private Score score;

    public Player(String name) {
        this.name = name;
        this.health = Constants.INITIAL_HEALTH;
        this.hasChosen = false;
        this.score = new Score();
    }

    public void makeChoice(Choice choice) {
        this.currentChoice = choice;
        this.hasChosen = true;
    }

    public void resetChoice() {
        this.currentChoice = null;
        this.hasChosen = false;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    // Getters and setters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public Choice getCurrentChoice() { return currentChoice; }
    public boolean hasChosen() { return hasChosen; }
    public Score getScore() { return score; }
}