package ui;

import util.GameConstants;
import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(GameFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Controls");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel player1Controls = new JLabel(GameConstants.PLAYER1_NAME + ": A - Rock, S - Scissors, D - Paper");
        player1Controls.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel player2Controls = new JLabel(GameConstants.PLAYER2_NAME + ": J - Rock, K - Scissors, L - Paper");
        player2Controls.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = new JButton("×");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> frame.showStartPanel());
        
        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createVerticalStrut(20));
        add(player1Controls);
        add(Box.createVerticalStrut(10));
        add(player2Controls);
        add(Box.createVerticalStrut(20));
        add(backButton);
        add(Box.createVerticalGlue());
    }
}