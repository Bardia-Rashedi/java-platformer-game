package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

// damages the player when they touch an Enemy, with a 1.5s cooldown
public class EnemyDamage implements CollisionListener {

    private final GameWorld world;
    private long lastHit = 0;

    public EnemyDamage(GameWorld world) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) {
            long now = System.currentTimeMillis();
            if (now - lastHit > 1500) {
                lastHit = now;
                if (world.getSoundManager() != null) {
                    world.getSoundManager().playHurt();
                }
                world.loseLife();
            }
        }
    }
}
