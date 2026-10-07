package game;

import city.cs.engine.*;

// base class for all power-ups — subclasses define what effect they apply
public abstract class PowerUp extends StaticBody {

    private static final Shape powerUpShape = new CircleShape(1f);

    public PowerUp(World world, String imagePath) {
        super(world, powerUpShape);
        addImage(new BodyImage(imagePath, 2.5f));
    }

    // called when the player collides with this power-up
    public abstract void applyEffect(Player player);
}
