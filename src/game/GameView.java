package game;

import city.cs.engine.UserView;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class GameView extends UserView {

    private final GameWorld world;
    private final Image background;
    protected int levelNumber;

    //calling the UserView constructor
    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        this.world = world;

        // pick the right background for this level
        int lvl = world.getLevelNumber();
        if (lvl == 2) {
            background = new ImageIcon("data/background2.png").getImage();
        } else if (lvl == 3) {
            background = new ImageIcon("data/background3.png").getImage();
        } else if (lvl == 4) {
            background = new ImageIcon("data/background4.png").getImage();
        } else {
            background = new ImageIcon("data/background.jpg").getImage();
        }

        //allows the view to receive keyboard input
        setFocusable(true);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    //drawing the background image
    @Override
    public void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    //drawing the HUD every frame
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 18));

        // heart health bar
        g.setColor(Color.WHITE);
        g.drawString("Lives:", 20, 30);
        int lives = world.getLives();
        for (int i = 0; i < 5; i++) {
            g.setColor(i < lives ? Color.RED : new Color(80, 80, 80, 180));
            g.fillOval(80 + i * 24, 14, 18, 18);
            g.setColor(Color.WHITE);
            g.drawOval(80 + i * 24, 14, 18, 18);
        }

        // level and score
        g.setColor(Color.WHITE);
        g.drawString("Level: " + world.getLevelNumber(), 20, 55);
        g.drawString("Score: " + world.getScore(), 20, 80);

        // high score
        int best = SaveManager.loadHighScore();
        if (best > 0) {
            g.setColor(new Color(255, 220, 50));
            g.drawString("Best: " + best, 20, 105);
        }
    }
}
