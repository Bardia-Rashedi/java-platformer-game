package game;

import org.jbox2d.common.Vec2;

public class Level2 extends GameWorld {

    public Level2() {
        super();
        levelNumber = 2;

        // ground
        for (int i = -5; i <= 5; i++) {
            Platform groundTile = new Platform(this);
            groundTile.setPosition(new Vec2(i * 7f, -22f));
        }

        // platforms - different layout
        Platform p1 = new Platform(this);
        p1.setPosition(new Vec2(2f, -15f));

        Platform p2 = new Platform(this);
        p2.setPosition(new Vec2(-20f, -10f));
        // player
        player = new Player(this);
        player.setPosition(new Vec2(-10f, -9f));

        // fires - blue flame for level 2
        Fire fire2 = new Fire(this, "data/blue_fire.png");
        fire2.setPosition(new Vec2(-11f, -15f));

        // collectible - apple for level 2
        Carrot carrot1 = new Carrot(this, "data/apple.png");
        carrot1.setPosition(new Vec2(1f, -12f));

        // gate
        Gate gate = new Gate(this);
        gate.setPosition(new Vec2(-21f, -8f));

        // listeners
        player.addCollisionListener(new FireDamage(this));
        player.addCollisionListener(new CarrotPickup(this));
    }
}