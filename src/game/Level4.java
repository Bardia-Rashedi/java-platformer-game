package game;

import org.jbox2d.common.Vec2;

public class Level4 extends GameWorld {

    public Level4() {
        super();

        levelNumber = 4;

        // very wide ground
        for (int i = -8; i <= 8; i++) {
            Platform groundTile = new Platform(this);
            groundTile.setPosition(new Vec2(i * 7f, -26f));
        }

        // ── SECTION 1: ground-level stepping stones (fires fill the gaps) ──
        Platform s1a = new Platform(this);
        s1a.setPosition(new Vec2(-28f, -22f));

        Platform s1b = new Platform(this);
        s1b.setPosition(new Vec2(-14f, -22f));

        Platform s1c = new Platform(this);
        s1c.setPosition(new Vec2(0f, -22f));

        Platform s1d = new Platform(this);
        s1d.setPosition(new Vec2(14f, -22f));

        Platform s1e = new Platform(this);
        s1e.setPosition(new Vec2(28f, -22f));

        // ── SECTION 2: mid-level zigzag ──
        Platform m1 = new Platform(this);
        m1.setPosition(new Vec2(-35f, -16f));

        Platform m2 = new Platform(this);
        m2.setPosition(new Vec2(-21f, -12f));

        Platform m3 = new Platform(this);
        m3.setPosition(new Vec2(-7f, -8f));

        Platform m4 = new Platform(this);
        m4.setPosition(new Vec2(7f, -12f));

        Platform m5 = new Platform(this);
        m5.setPosition(new Vec2(21f, -8f));

        Platform m6 = new Platform(this);
        m6.setPosition(new Vec2(35f, -12f));

        // ── SECTION 3: high-level final climb ──
        Platform h1 = new Platform(this);
        h1.setPosition(new Vec2(21f, -4f));

        Platform h2 = new Platform(this);
        h2.setPosition(new Vec2(7f, 0f));

        Platform h3 = new Platform(this);
        h3.setPosition(new Vec2(21f, 4f));

        Platform h4 = new Platform(this);
        h4.setPosition(new Vec2(35f, 8f));   // gate platform

        // player starts far left, low
        player = new Player(this);
        player.setPosition(new Vec2(-42f, -24f));

        // ── FIRES - green flame ──
        // gap fires on ground level (force use of stepping stones)
        Fire gf1 = new Fire(this, "data/green_fire.png");
        gf1.setPosition(new Vec2(-21f, -25f));

        Fire gf2 = new Fire(this, "data/green_fire.png");
        gf2.setPosition(new Vec2(-7f, -25f));

        Fire gf3 = new Fire(this, "data/green_fire.png");
        gf3.setPosition(new Vec2(7f, -25f));

        Fire gf4 = new Fire(this, "data/green_fire.png");
        gf4.setPosition(new Vec2(21f, -25f));

        // fires on stepping stones (s1a, s1c, s1e have fire - s1b and s1d safe)
        Fire sf1 = new Fire(this, "data/green_fire.png");
        sf1.setPosition(new Vec2(-28f, -21f));  // on s1a

        Fire sf2 = new Fire(this, "data/green_fire.png");
        sf2.setPosition(new Vec2(0f, -21f));    // on s1c

        Fire sf3 = new Fire(this, "data/green_fire.png");
        sf3.setPosition(new Vec2(28f, -21f));   // on s1e

        // fires on mid-level platforms
        Fire mf1 = new Fire(this, "data/green_fire.png");
        mf1.setPosition(new Vec2(-21f, -11f));  // on m2

        Fire mf2 = new Fire(this, "data/green_fire.png");
        mf2.setPosition(new Vec2(7f, -11f));    // on m4

        Fire mf3 = new Fire(this, "data/green_fire.png");
        mf3.setPosition(new Vec2(35f, -11f));   // on m6

        // fires on high platforms
        Fire hf1 = new Fire(this, "data/green_fire.png");
        hf1.setPosition(new Vec2(21f, -3f));    // on h1

        Fire hf2 = new Fire(this, "data/green_fire.png");
        hf2.setPosition(new Vec2(7f, 1f));      // on h2

        // ── COLLECTIBLES - pineapple, placed in safe spots ──
        Carrot c1 = new Carrot(this, "data/pineapple.png");
        c1.setPosition(new Vec2(-14f, -21f));   // on safe stepping stone s1b

        Carrot c2 = new Carrot(this, "data/pineapple.png");
        c2.setPosition(new Vec2(-35f, -15f));   // on m1

        Carrot c3 = new Carrot(this, "data/pineapple.png");
        c3.setPosition(new Vec2(-7f, -7f));     // on m3

        Carrot c4 = new Carrot(this, "data/pineapple.png");
        c4.setPosition(new Vec2(21f, 5f));      // on h3 near gate

        // gate at top right
        Gate gate = new Gate(this);
        gate.setPosition(new Vec2(35f, 10f));

        // enemies - patrol across mid-level platforms
        Enemy e1 = new Enemy(this, -39f, -31f);
        e1.setPosition(new Vec2(-35f, -14f));   // on m1

        Enemy e2 = new Enemy(this, -11f, -3f);
        e2.setPosition(new Vec2(-7f, -6f));     // on m3

        Enemy e3 = new Enemy(this, 17f, 25f);
        e3.setPosition(new Vec2(21f, -2f));     // on h1

        // speed boost on safe stepping stone s1b - helps player survive the gauntlet
        SpeedBoost boost = new SpeedBoost(this);
        boost.setPosition(new Vec2(-14f, -20f));

        // collision listeners
        player.addCollisionListener(new FireDamage(this));
        player.addCollisionListener(new CarrotPickup(this));
        player.addCollisionListener(new EnemyDamage(this));
        player.addCollisionListener(new PowerUpCollision(this));
    }
}
