package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class GateCollision implements CollisionListener {

    private final Game game;

    public GateCollision(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Gate) {
            // play portal sound when reaching the gate
            if (game.getSoundManager() != null) {
                game.getSoundManager().playPortal();
            }
            game.goToNextLevel();
        }
    }
}