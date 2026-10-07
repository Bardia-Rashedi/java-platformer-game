package game;

import javax.swing.*;
import java.awt.*;

// used as the frame's glass pane so it overlays on top of the game view
public class PauseMenuPanel extends JPanel {

    public PauseMenuPanel(Game game) {
        setOpaque(false);
        // GridBagLayout centres the inner card on the screen
        setLayout(new GridBagLayout());

        // inner card with a solid dark-green background
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(15, 60, 15, 230));
        card.setBorder(BorderFactory.createEmptyBorder(35, 70, 35, 70));

        JLabel pauseLabel = new JLabel("PAUSED");
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 52));
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resumeBtn  = makeButton("Resume");
        JButton quitBtn    = makeButton("Quit to Menu");

        resumeBtn.addActionListener(e -> game.resumeGame());
        quitBtn.addActionListener(e   -> game.quitToMenu());

        card.add(pauseLabel);
        card.add(Box.createVerticalStrut(25));
        card.add(resumeBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(quitBtn);

        add(card);
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(40, 120, 40));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(210, 46));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // semi-transparent black overlay behind the card
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
