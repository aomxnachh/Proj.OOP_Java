package ui;

import util.GameConstants;
import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    private GameFrame gameFrame;
    
    public StartPanel(GameFrame frame) {
        this.gameFrame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(GameConstants.GAME_TITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> gameFrame.showGamePanel());
        
        JButton controlButton = new JButton("Control");
        controlButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlButton.addActionListener(e -> gameFrame.showControlPanel());
        
        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createVerticalStrut(20));
        add(startButton);
        add(Box.createVerticalStrut(10));
        add(controlButton);
        add(Box.createVerticalGlue());
    }
}