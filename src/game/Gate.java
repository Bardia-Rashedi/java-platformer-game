package game;

import city.cs.engine.*;

public class Gate extends StaticBody {
    private static final Shape getShape = new BoxShape(1f,2f);

    public Gate(World world) {
        super(world, getShape);
        addImage(new BodyImage("data/gate.png",4f));
    }
}
