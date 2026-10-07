package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {

    private static final Shape platformShape = new BoxShape(3.5f, 0.7f);

    public Platform(World world) {
        super(world, platformShape);

        addImage(new BodyImage("data/platform.png",7f));
    }
}
