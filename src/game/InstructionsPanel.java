package game;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {

    private final Image background;

    public InstructionsPanel(Game game) {
        background = new ImageIcon("data/background.jpg").getImage();
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // --- title ---
        JLabel title = new JLabel("How To Play", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 52));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(60, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // --- instructions using BoxLayout ---
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // each string is one line; empty string = spacer
        String[] lines = {
            "Controls",
            "  Left / Right Arrow  or  A / D  -  Move",
            "  Up Arrow  or  W  -  Jump",
            "  ESC  -  Pause / Resume",
            "",
            "Objective",
            "  Reach the portal to advance to the next level",
            "  Collect fruit to gain +2 health and +10 score",
            "  Avoid fire or you lose a life",
            "",
            "Lives",
            "  You start with 3 lives  (max 5)",
            "  Losing all lives ends the game",
            "",
            "Score",
            "  Each fruit collected gives +10 points",
            "  Score carries across all levels",
            "  Your final score is shown on the end screen",
        };

        for (String line : lines) {
            JLabel lbl;
            if (line.isEmpty()) {
                lbl = new JLabel(" ");
            } else if (!line.startsWith(" ")) {
                // section heading — bold and slightly larger
                lbl = new JLabel(line);
                lbl.setFont(new Font("Arial", Font.BOLD, 19));
                lbl.setForeground(new Color(180, 255, 150));
            } else {
                lbl = new JLabel(line);
                lbl.setFont(new Font("Arial", Font.PLAIN, 17));
                lbl.setForeground(Color.WHITE);
            }
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbl.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
            infoPanel.add(lbl);
        }

        // --- back button ---
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(30, 80, 30, 210));
        backBtn.setOpaque(true);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setMaximumSize(new Dimension(200, 46));
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> game.showMainMenu());

        infoPanel.add(Box.createVerticalStrut(25));
        infoPanel.add(backBtn);
        infoPanel.add(Box.createVerticalStrut(20));

        // wrap in a transparent scroll pane so content is always reachable
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
