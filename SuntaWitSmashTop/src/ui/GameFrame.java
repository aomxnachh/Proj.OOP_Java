package ui;

import util.GameConstants;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GameFrame() {
        setTitle(GameConstants.GAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        startPanel = new StartPanel(this);
        gamePanel = new GamePanel(this);
        controlPanel = new ControlPanel(this);
        
        mainPanel.add(startPanel, "start");
        mainPanel.add(gamePanel, "game");
        mainPanel.add(controlPanel, "control");
        
        add(mainPanel);
        showStartPanel();
    }
    
    public void showStartPanel() {
        cardLayout.show(mainPanel, "start");
    }
    
    public void showGamePanel() {
        cardLayout.show(mainPanel, "game");
        gamePanel.startGame();
    }
    
    public void showControlPanel() {
        cardLayout.show(mainPanel, "control");
    }
}