package game;

import city.cs.engine.World;

public class GameWorld extends World {

    protected Player player;
    protected int lives = 3;
    protected int levelNumber;
    protected int score = 0;
    private boolean gameWon = false;
    private SoundManager soundManager;


    public int getLives() {
        return lives;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
            System.out.println("Lives: " + lives);
        }

        if (lives <= 0) {
            stop();
        }
    }

    public void gainHealth(int amount) {
        lives += amount;

        if (lives > 5) {
            lives = 5;
        }

        System.out.println("Lives: " + lives);
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }
}