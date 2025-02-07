package model;

import util.GameConstants;

public class Player {
    private String name;
    private int hp;
    private Choice currentChoice;
    
    public Player(String name) {
        this.name = name;
        this.hp = GameConstants.INITIAL_HP;
    }
    
    public void setChoice(Choice choice) {
        this.currentChoice = choice;
    }
    
    public Choice getChoice() {
        return currentChoice;
    }
    
    public void damage() {
        hp -= GameConstants.DAMAGE_AMOUNT;
    }
    
    public int getHP() {
        return hp;
    }
    
    public String getName() {
        return name;
    }
    
    public void reset() {
        hp = GameConstants.INITIAL_HP;
        currentChoice = null;
    }
    
}

