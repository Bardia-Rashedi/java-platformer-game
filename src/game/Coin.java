package game;

import city.cs.engine.*;
import java.awt.Color;

public class Coin extends StaticBody {

    public Coin(World world) {
        super(world);
        // ghost fixture so the player can walk through and collect it
        GhostlyFixture gf = new GhostlyFixture(this, new CircleShape(0.5f));
        gf.setFill(Color.YELLOW);
        gf.setLineColor(Color.ORANGE);
    }
}
