package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

// handles the player collecting a power-up
public class PowerUpCollision implements CollisionListener {

    private final GameWorld world;

    public PowerUpCollision(GameWorld world) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof PowerUp) {
            PowerUp powerUp = (PowerUp) e.getOtherBody();
            powerUp.applyEffect(world.getPlayer());
            e.getOtherBody().destroy();
        }
    }
}
