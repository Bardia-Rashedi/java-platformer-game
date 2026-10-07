package game;

import org.jbox2d.common.Vec2;

public class Level3 extends GameWorld {

    public Level3() {
        super();

        levelNumber = 3;

        // wide ground
        for (int i = -6; i <= 6; i++) {
            Platform groundTile = new Platform(this);
            groundTile.setPosition(new Vec2(i * 7f, -22f));
        }

        // zigzag platform path - player must jump back and forth to climb
        // start far right, gate far left at top

        Platform p1 = new Platform(this);
        p1.setPosition(new Vec2(14f, -18f));   // step up from ground, right side

        Platform p2 = new Platform(this);
        p2.setPosition(new Vec2(0f, -14f));    // jump left and up

        Platform p3 = new Platform(this);
        p3.setPosition(new Vec2(-14f, -10f));  // jump left and up

        Platform p4 = new Platform(this);
        p4.setPosition(new Vec2(7f, -6f));     // jump right and up

        Platform p5 = new Platform(this);
        p5.setPosition(new Vec2(-7f, -2f));    // jump left and up

        Platform p6 = new Platform(this);
        p6.setPosition(new Vec2(14f, 2f));     // jump right and up

        Platform p7 = new Platform(this);
        p7.setPosition(new Vec2(-7f, 6f));     // jump left and up

        Platform p8 = new Platform(this);
        p8.setPosition(new Vec2(-21f, 10f));   // jump left to gate platform

        // player starts far right on ground
        player = new Player(this);
        player.setPosition(new Vec2(28f, -20f));

        // fires - pink flame, blocking ground and some platforms
        Fire f1 = new Fire(this, "data/pink_fire.png");
        f1.setPosition(new Vec2(7f, -21f));    // blocks easy ground walk

        Fire f2 = new Fire(this, "data/pink_fire.png");
        f2.setPosition(new Vec2(-7f, -21f));   // blocks ground

        Fire f3 = new Fire(this, "data/pink_fire.png");
        f3.setPosition(new Vec2(-21f, -21f));  // blocks ground left side

        Fire f4 = new Fire(this, "data/pink_fire.png");
        f4.setPosition(new Vec2(0f, -13f));    // on platform p2 - risky landing

        Fire f5 = new Fire(this, "data/pink_fire.png");
        f5.setPosition(new Vec2(7f, -5f));     // on platform p4

        Fire f6 = new Fire(this, "data/pink_fire.png");
        f6.setPosition(new Vec2(14f, 3f));     // on platform p6

        Fire f7 = new Fire(this, "data/pink_fire.png");
        f7.setPosition(new Vec2(-7f, 7f));     // on platform p7 - near gate

        // collectibles - bananas, placed in safe spots on platforms
        Carrot b1 = new Carrot(this, "data/banana.png");
        b1.setPosition(new Vec2(14f, -17f));   // on p1

        Carrot b2 = new Carrot(this, "data/banana.png");
        b2.setPosition(new Vec2(-14f, -9f));   // on p3

        Carrot b3 = new Carrot(this, "data/banana.png");
        b3.setPosition(new Vec2(-7f, -1f));    // on p5

        Carrot b4 = new Carrot(this, "data/banana.png");
        b4.setPosition(new Vec2(-21f, 11f));   // on gate platform p8

        // gate is far left at the top
        Gate gate = new Gate(this);
        gate.setPosition(new Vec2(-21f, 12f));

        // enemies - patrol on p3 and p5
        Enemy e1 = new Enemy(this, -18f, -10f);
        e1.setPosition(new Vec2(-14f, -8f));

        Enemy e2 = new Enemy(this, -11f, -3f);
        e2.setPosition(new Vec2(-7f, 0f));

        // speed boost power-up on p1 - reward for starting platform
        SpeedBoost boost = new SpeedBoost(this);
        boost.setPosition(new Vec2(14f, -16f));

        // listeners
        player.addCollisionListener(new FireDamage(this));
        player.addCollisionListener(new CarrotPickup(this));
        player.addCollisionListener(new EnemyDamage(this));
        player.addCollisionListener(new PowerUpCollision(this));
    }
}
