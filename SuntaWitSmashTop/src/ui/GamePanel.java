package ui;

import model.Player;
import model.Choice;
import util.GameConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private Player player1;
    private Player player2;
    private Timer countdownTimer;
    private Timer delayTimer;
    private int countdown;
    private JLabel countdownLabel;
    private JLabel player1HP;
    private JLabel player2HP;
    private JLabel statusLabel;
    
    public GamePanel(GameFrame frame) {
        player1 = new Player(GameConstants.PLAYER1_NAME);
        player2 = new Player(GameConstants.PLAYER2_NAME);
        
        setLayout(new BorderLayout());
        setupUI();
        setupKeyBindings();
        
        countdownTimer = new Timer(1000, e -> updateCountdown());
        delayTimer = new Timer(GameConstants.DELAY_TIME, e -> {
            delayTimer.stop();
            statusLabel.setText("START");
            startRound();
        });
    }

    public void startGame() {
        countdown = GameConstants.COUNTDOWN_TIME;
        countdownLabel.setText("Countdown: " + countdown);
        countdownTimer.start();
    }

    private void setupUI() {
        countdownLabel = new JLabel("Countdown: " + countdown);
        player1HP = new JLabel("Player 1 HP: " + player1.getHP());
        player2HP = new JLabel("Player 2 HP: " + player2.getHP());
        statusLabel = new JLabel("Status: Waiting");

        add(countdownLabel, BorderLayout.NORTH);
        add(player1HP, BorderLayout.WEST);
        add(player2HP, BorderLayout.EAST);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void setupKeyBindings() {
        
    }

    private void updateCountdown() {
        if (countdown > 0) {
            countdown--;
            countdownLabel.setText("Countdown: " + countdown);
        } else {
            countdownTimer.stop();
            statusLabel.setText("GO!");
            startRound();
        }
    }

    private void startRound() {
        
    }

}