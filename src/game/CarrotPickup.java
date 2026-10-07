package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CarrotPickup implements CollisionListener {

    private final GameWorld world;

    public CarrotPickup(GameWorld world) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Carrot) {
            // play eating sound when picking up carrot
            if (world.getSoundManager() != null) {
                world.getSoundManager().playEating();
            }
            world.gainHealth(2);
            world.addScore(10);
            e.getOtherBody().destroy();
        }
    }
}