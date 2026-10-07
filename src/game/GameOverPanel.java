package game;

import javax.swing.*;
import java.awt.*;

// used as the frame's glass pane to overlay game over / win on top of the game
public class GameOverPanel extends JPanel {

    public GameOverPanel(Game game, boolean won, int score) {
        setOpaque(false);
        setLayout(new GridBagLayout()); // centres the card on screen

        // inner card with dark background
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(15, 60, 15, 235));
        card.setBorder(BorderFactory.createEmptyBorder(35, 70, 35, 70));

        // main message
        JLabel msgLabel = new JLabel(won ? "YOU WON!" : "GAME OVER");
        msgLabel.setFont(new Font("Arial", Font.BOLD, 58));
        msgLabel.setForeground(won ? Color.YELLOW : Color.RED);
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // score display
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // high score display
        int best = SaveManager.loadHighScore();
        JLabel bestLabel = new JLabel(score >= best ? "New Best: " + score + "!" : "Best: " + best);
        bestLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bestLabel.setForeground(new Color(255, 220, 50));
        bestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // back to menu button
        JButton menuBtn = new JButton("Back to Menu");
        menuBtn.setFont(new Font("Arial", Font.BOLD, 20));
        menuBtn.setForeground(Color.WHITE);
        menuBtn.setBackground(new Color(40, 120, 40));
        menuBtn.setOpaque(true);
        menuBtn.setBorderPainted(false);
        menuBtn.setFocusPainted(false);
        menuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuBtn.setMaximumSize(new Dimension(210, 46));
        menuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBtn.addActionListener(e -> game.showMainMenu());

        card.add(msgLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(scoreLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(bestLabel);
        card.add(Box.createVerticalStrut(25));
        card.add(menuBtn);

        add(card);
    }

    // semi-transparent dark overlay behind the card
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
