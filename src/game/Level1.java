package game;

import org.jbox2d.common.Vec2;

public class Level1 extends GameWorld {

    public Level1() {
        super();
        levelNumber = 1;

        // ground
        for (int i = -5; i <= 5; i++) {
            Platform groundTile = new Platform(this);
            groundTile.setPosition(new Vec2(i * 7f, -22f));
        }

        // platforms
        Platform p1 = new Platform(this);
        p1.setPosition(new Vec2(-7f, -15f));

        // player
        player = new Player(this);
        player.setPosition(new Vec2(0, -9));

        // fire
        Fire fire1 = new Fire(this);
        fire1.setPosition(new Vec2(-6f, -19.5f));


        // carrot
        Carrot carrot1 = new Carrot(this);
        carrot1.setPosition(new Vec2(6f, -20f));

        // gate to next level
        Gate gate = new Gate(this);
        gate.setPosition(new Vec2(-7f, -13f));

        // listeners
        player.addCollisionListener(new FireDamage(this));
        player.addCollisionListener(new CarrotPickup(this));
    }
}
