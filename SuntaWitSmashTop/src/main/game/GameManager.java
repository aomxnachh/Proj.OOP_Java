package main.game;

import main.enums.*;
import main.util.*;
import main.exception.GameException;

public class GameManager {
    private static GameManager instance;
    private Game currentGame;
    private GameState gameState;
    private Timer countdownTimer;
    private Timer roundTimer;
    private final Logger logger;

    private GameManager() {
        this.logger = Logger.getInstance();
        this.countdownTimer = new Timer(Constants.COUNTDOWN_TIME);
        this.roundTimer = new Timer(Constants.ROUND_TIME);
        this.gameState = GameState.MENU;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startNewGame() {
        try {
            currentGame = new Game();
            setGameState(GameState.COUNTDOWN);
            countdownTimer.reset();
            logger.log("New game started");
        } catch (Exception e) {
            logger.error("Failed to start new game", e);
            throw new GameException("Failed to start new game");
        }
    }

    public void setGameState(GameState newState) {
        if (newState == null) {
            throw new GameException("Invalid game state");
        }
        this.gameState = newState;
        logger.log("Game state changed to: " + newState);
    }

    public String getDisplayMessage() {
        switch (gameState) {
            case COUNTDOWN:
                return Constants.COUNTDOWN_MESSAGE;
            case PLAYING:
                return Constants.START_MESSAGE;
            case GAME_OVER:
                return getWinnerMessage();
            default:
                return "";
        }
    }

    private String getWinnerMessage() {
        if (currentGame == null) return "";
        String winner = currentGame.getWinnerName();
        return winner.isEmpty() ? Constants.DRAW_MESSAGE : winner + Constants.WIN_MESSAGE;
    }

    public void update(double deltaTime) {
        try {
            switch (gameState) {
                case COUNTDOWN:
                    updateCountdown(deltaTime);
                    break;
                case PLAYING:
                    updateRound(deltaTime);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error during game update", e);
            throw new GameException("Game update failed");
        }
    }

    private void updateCountdown(double deltaTime) {
        countdownTimer.update(deltaTime);
        if (countdownTimer.isFinished()) {
            setGameState(GameState.PLAYING);
            roundTimer.reset();
        }
    }

    private void updateRound(double deltaTime) {
        if (currentGame != null) {
            roundTimer.update(deltaTime);
            if (roundTimer.isFinished()) {
                currentGame.handleTimeout();
                if (gameState != GameState.GAME_OVER) {
                    roundTimer.reset();
                }
            }
        }
    }

    public void rematch() {
        if (gameState == GameState.GAME_OVER) {
            startNewGame();
        }
    }

    public void returnToMenu() {
        setGameState(GameState.MENU);
        currentGame = null;
    }

    // Getters
    public Game getCurrentGame() { return currentGame; }
    public GameState getGameState() { return gameState; }
    public Timer getCountdownTimer() { return countdownTimer; }
    public Timer getRoundTimer() { return roundTimer; }
    public double getRoundTimeRemaining() { return roundTimer.getCurrentTime(); }
    public double getCountdownTimeRemaining() { return countdownTimer.getCurrentTime(); }
}