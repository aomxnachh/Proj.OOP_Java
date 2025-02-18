package main.game;

import main.model.*;
import main.enums.*;
import main.util.*;
import main.exception.GameException;

public class Game {
    private final Player player1;
    private final Player player2;
    private GameResult result;
    private final Logger logger;
    private boolean isRoundActive;

    public Game() {
        this.player1 = new Player(Constants.PLAYER1_NAME);
        this.player2 = new Player(Constants.PLAYER2_NAME);
        this.logger = Logger.getInstance();
        this.isRoundActive = true;
    }

    public void makeChoice(boolean isPlayer1, Choice choice) {
        try {
            if (!isRoundActive || choice == null) return;

            Player player = isPlayer1 ? player1 : player2;
            if (player != null && !player.hasChosen()) {
                player.makeChoice(choice);
                logger.log(player.getName() + " made choice: " + choice);

                if (player1.hasChosen() && player2.hasChosen()) {
                    resolveRound();
                }
            }
        } catch (Exception e) {
            logger.error("Error making choice", e);
            throw new GameException("Failed to make choice");
        }
    }

    private void resolveRound() {
        if (!isRoundActive) return;

        Choice p1Choice = player1.getCurrentChoice();
        Choice p2Choice = player2.getCurrentChoice();

        if (p1Choice == null || p2Choice == null) {
            handleTimeout();
            return;
        }

        determineWinner(p1Choice, p2Choice);
        isRoundActive = false;
    }

    private void determineWinner(Choice p1Choice, Choice p2Choice) {
        if (p1Choice == p2Choice) {
            handleDraw();
        } else if (p1Choice.beats(p2Choice)) {
            handleWin(player1, player2);
        } else {
            handleWin(player2, player1);
        }
        checkGameOver();
    }

    private void handleDraw() {
        player1.takeDamage(Constants.DRAW_DAMAGE);
        player2.takeDamage(Constants.DRAW_DAMAGE);
        result = GameResult.DRAW;
        player1.getScore().addDraw();
        player2.getScore().addDraw();
        logger.log("Round ended in draw");
    }

    private void handleWin(Player winner, Player loser) {
        loser.takeDamage(Constants.NORMAL_DAMAGE);
        result = winner == player1 ? GameResult.WIN : GameResult.LOSE;
        winner.getScore().addWin();
        loser.getScore().addLoss();
        logger.log(winner.getName() + " won the round");
    }

    public void handleTimeout() {
        if (!isRoundActive) return;

        boolean p1Chosen = player1.hasChosen();
        boolean p2Chosen = player2.hasChosen();

        if (!p1Chosen && !p2Chosen) {
            handleTimeoutDraw();
        } else if (!p1Chosen) {
            handleTimeoutWin(player2, player1);
        } else if (!p2Chosen) {
            handleTimeoutWin(player1, player2);
        }

        isRoundActive = false;
        checkGameOver();
    }

    private void handleTimeoutDraw() {
        player1.takeDamage(Constants.TIMEOUT_DAMAGE);
        player2.takeDamage(Constants.TIMEOUT_DAMAGE);
        result = GameResult.TIMEOUT;
        player1.getScore().addDraw();
        player2.getScore().addDraw();
        logger.log("Timeout - Both players failed to choose");
    }

    private void handleTimeoutWin(Player winner, Player loser) {
        loser.takeDamage(Constants.TIMEOUT_DAMAGE);
        result = GameResult.TIMEOUT;
        winner.getScore().addWin();
        loser.getScore().addLoss();
        logger.log("Timeout - " + loser.getName() + " failed to choose");
    }

    private void checkGameOver() {
        if (player1.getHealth() <= 0 || player2.getHealth() <= 0) {
            GameManager.getInstance().setGameState(GameState.GAME_OVER);
        } else {
            prepareNextRound();
        }
    }

    private void prepareNextRound() {
        player1.resetChoice();
        player2.resetChoice();
        isRoundActive = true;
    }

    public String getWinnerName() {
        if (player1.getHealth() <= 0) return player2.getName();
        if (player2.getHealth() <= 0) return player1.getName();
        return "";
    }

    // Getters
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public GameResult getResult() { return result; }
    public boolean isRoundActive() { return isRoundActive; }
}