package game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private final Image background;

    public MainMenuPanel(Game game, SoundManager soundManager) {
        background = new ImageIcon("data/background.jpg").getImage();
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // --- title at the top ---
        JLabel title = new JLabel("City Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setForeground(Color.WHITE);
        title.setOpaque(false);
        title.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));
        add(title, BorderLayout.NORTH);

        // --- buttons in the centre using BoxLayout ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton playBtn         = makeButton("Play");
        JButton instructionsBtn = makeButton("Instructions");
        JButton muteBtn         = makeButton("Mute");
        JButton quitBtn         = makeButton("Quit");

        // show high score under the title
        int highScore = SaveManager.loadHighScore();
        JLabel highScoreLabel = new JLabel(highScore > 0 ? "Best Score: " + highScore : " ");
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        highScoreLabel.setForeground(new Color(255, 220, 50));
        highScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // volume label + slider
        JLabel volLabel = new JLabel("Volume");
        volLabel.setFont(new Font("Arial", Font.BOLD, 15));
        volLabel.setForeground(Color.WHITE);
        volLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSlider volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setUI(new BasicSliderUI(volumeSlider));
        volumeSlider.setOpaque(false);
        volumeSlider.setBackground(new Color(0, 0, 0, 0));
        volumeSlider.setForeground(Color.WHITE);
        volumeSlider.setMaximumSize(new Dimension(200, 35));
        volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeSlider.setFocusable(false);
        volumeSlider.addChangeListener(e -> {
            // minimum 0.01 so the audio control never receives 0 (breaks log scale)
            double v = Math.max(0.01, volumeSlider.getValue() / 50.0);
            soundManager.setVolume(v);
        });

        // button actions
        playBtn.addActionListener(e -> game.startGame());

        instructionsBtn.addActionListener(e -> game.showInstructions());

        muteBtn.addActionListener(e -> {
            boolean nowMuted = !soundManager.isMuted();
            soundManager.setMuted(nowMuted);
            muteBtn.setText(nowMuted ? "Unmute" : "Mute");
        });

        quitBtn.addActionListener(e -> System.exit(0));

        // lay everything out
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(highScoreLabel);
        buttonPanel.add(Box.createVerticalStrut(14));
        buttonPanel.add(playBtn);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(instructionsBtn);
        buttonPanel.add(Box.createVerticalStrut(18));
        buttonPanel.add(volLabel);
        buttonPanel.add(Box.createVerticalStrut(4));
        buttonPanel.add(volumeSlider);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(muteBtn);
        buttonPanel.add(Box.createVerticalStrut(12));
        buttonPanel.add(quitBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // helper to build a styled button
    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(30, 80, 30, 210));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(210, 46));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // draw background + dark overlay so text is readable
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.setColor(new Color(0, 0, 0, 130));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
