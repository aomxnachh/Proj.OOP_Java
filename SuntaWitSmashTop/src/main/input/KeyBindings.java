package main.input;

import main.enums.Choice;
import java.util.HashMap;
import java.util.Map;

public class KeyBindings {
    private Map<Character, Choice> player1Keys;
    private Map<Character, Choice> player2Keys;

    public KeyBindings() {
        initializeKeyBindings();
    }

    private void initializeKeyBindings() {
        player1Keys = new HashMap<>();
        player1Keys.put('a', Choice.ROCK);
        player1Keys.put('s', Choice.PAPER);
        player1Keys.put('d', Choice.SCISSORS);

        player2Keys = new HashMap<>();
        player2Keys.put('j', Choice.ROCK);
        player2Keys.put('k', Choice.PAPER);
        player2Keys.put('l', Choice.SCISSORS);
    }

    public Choice getChoice(char key) {
        if (player1Keys.containsKey(key)) return player1Keys.get(key);
        if (player2Keys.containsKey(key)) return player2Keys.get(key);
        return null;
    }

    public boolean isPlayer1Key(char key) {
        return player1Keys.containsKey(key);
    }
}