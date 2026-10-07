package game;

import city.cs.engine.*;

public class Fire extends StaticBody {

    private static final Shape fireShape = new BoxShape(1f,1f);

    // default orange fire
    public Fire(World world) {
        this(world, "data/fire.png");
    }

    // custom fire image per level
    public Fire(World world, String imagePath) {
        super(world, fireShape);
        addImage(new BodyImage(imagePath, 3f));
    }
}
