package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends Walker {

    private static final Shape playerShape = new BoxShape(1.8f, 1.1f);
    private float walkSpeed = 7f;

    public float getWalkSpeed() { return walkSpeed; }
    public void setWalkSpeed(float speed) { this.walkSpeed = speed; }

    public Player(World world) {
        super(world, playerShape);

        // starting position
        setPosition(new Vec2(0f, -10.2f));

        // character image
        addImage(new BodyImage("data/character.gif", 4f));

    }
}
