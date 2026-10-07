package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerController extends KeyAdapter {

    private final Player player;
    private final SoundManager soundManager;

    public PlayerController(Player player, SoundManager soundManager) {
        this.player = player;
        this.soundManager = soundManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // move player left
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            player.startWalking(-player.getWalkSpeed());
        }
        // move player right
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            player.startWalking(player.getWalkSpeed());
        }
        // jump - also plays jump sound
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            player.jump(13);
            soundManager.playJumping();
        }
    }

    @Override
    // called when the user releases a key
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // stop walking when movement keys are released
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A
                || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            player.stopWalking();
        }
    }
}
