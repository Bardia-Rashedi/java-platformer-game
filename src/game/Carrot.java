package game;

import city.cs.engine.*;

public class Carrot extends StaticBody {

    private static final Shape carrotShape = new BoxShape(1f, 1f);

    // default carrot
    public Carrot(World world) {
        this(world, "data/Carrot.png");
    }

    // custom fruit/veggie image per level
    public Carrot(World world, String imagePath) {
        super(world, carrotShape);
        addImage(new BodyImage(imagePath, 2f));
    }
}