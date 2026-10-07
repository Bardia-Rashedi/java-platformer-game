package game;

import city.cs.engine.World;
import javax.swing.Timer;

// power-up that doubles the player's walk speed for 5 seconds
public class SpeedBoost extends PowerUp {

    public SpeedBoost(World world) {
        super(world, "data/apple.png");
    }

    @Override
    public void applyEffect(Player player) {
        player.setWalkSpeed(14);  // double normal speed
        // reset after 5 seconds
        Timer timer = new Timer(5000, e -> player.setWalkSpeed(7));
        timer.setRepeats(false);
        timer.start();
    }
}
