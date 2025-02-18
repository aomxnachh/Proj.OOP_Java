package main.input;

import main.game.GameManager;
import main.enums.Choice;
import main.enums.GameState;

public class InputManager {
    private static InputManager instance;
    private KeyBindings keyBindings;

    private InputManager() {
        this.keyBindings = new KeyBindings();
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void handleKeyPress(char key) {
        if (GameManager.getInstance().getGameState() != GameState.PLAYING) return;

        Choice choice = keyBindings.getChoice(key);
        if (choice != null) {
            boolean isPlayer1 = keyBindings.isPlayer1Key(key);
            GameManager.getInstance().getCurrentGame().makeChoice(isPlayer1, choice);
        }
    }
}