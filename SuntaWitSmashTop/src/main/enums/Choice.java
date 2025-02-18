package main.enums;

public enum Choice {
    ROCK, PAPER, SCISSORS;
    
    public boolean beats(Choice other) {
        return (this == ROCK && other == SCISSORS) ||
               (this == SCISSORS && other == PAPER) ||
               (this == PAPER && other == ROCK);
    }
}