package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class FireDamage implements CollisionListener {

    private final GameWorld world;
    // stop the player losing all lives instantly when touching fire
    private long lastHitTime = 0;
    private static final long COOLDOWN_MS = 1500;

    public FireDamage(GameWorld world) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Fire) {
            long now = System.currentTimeMillis();
            if (now - lastHitTime >= COOLDOWN_MS) {
                lastHitTime = now;
                // play hurt sound when touching fire
                if (world.getSoundManager() != null) {
                    world.getSoundManager().playHurt();
                }
                world.loseLife();
            }
        }
    }

}
