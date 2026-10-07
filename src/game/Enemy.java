package game;

import city.cs.engine.*;

// an enemy that patrols back and forth between two x positions
// damages the player on contact
public class Enemy extends Walker implements StepListener {

    private static final Shape enemyShape = new BoxShape(1.2f, 1f);
    private final float leftLimit;
    private final float rightLimit;
    private static final float SPEED = 4f;
    private boolean movingRight = true;

    public Enemy(GameWorld world, float leftLimit, float rightLimit) {
        super(world, enemyShape);
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        // orange fire image — visually distinct from level-specific coloured fires
        addImage(new BodyImage("data/fire.png", 2.5f));
        world.addStepListener(this);
        startWalking(SPEED);
    }

    @Override
    public void preStep(StepEvent e) {
        float x = getPosition().x;
        // reverse direction at patrol limits
        if (x >= rightLimit && movingRight) {
            movingRight = false;
            startWalking(-SPEED);
        } else if (x <= leftLimit && !movingRight) {
            movingRight = true;
            startWalking(SPEED);
        }
    }

    @Override
    public void postStep(StepEvent e) {}
}
