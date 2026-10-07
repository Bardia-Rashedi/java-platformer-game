package game;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    public ControlPanel(SoundManager soundManager) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.DARK_GRAY);

        // volume label
        JLabel volumeLabel = new JLabel("Volume:");
        volumeLabel.setForeground(Color.WHITE);
        add(volumeLabel);

        // volume slider (0 to 100, default 50)
        JSlider volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setPreferredSize(new Dimension(150, 25));
        volumeSlider.setFocusable(false); // dont steal keyboard focus from the game
        volumeSlider.addChangeListener(e -> {
            // map 0-100 to 0.0-2.0
            double vol = volumeSlider.getValue() / 50.0;
            soundManager.setVolume(vol);
        });
        add(volumeSlider);

        // mute/unmute button
        JButton muteButton = new JButton("Mute");
        muteButton.setFocusable(false);
        muteButton.addActionListener(e -> {
            boolean nowMuted = !soundManager.isMuted();
            soundManager.setMuted(nowMuted);
            muteButton.setText(nowMuted ? "Unmute" : "Mute");
        });
        add(muteButton);
    }
}
