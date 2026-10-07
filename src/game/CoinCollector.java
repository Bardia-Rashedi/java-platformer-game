package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CoinCollector implements CollisionListener {

    private final GameWorld world;

    public CoinCollector(GameWorld world) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) {
            world.coinCollected((Coin) e.getOtherBody());
        }
    }
}
