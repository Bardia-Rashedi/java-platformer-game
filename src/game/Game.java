package game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private GameWorld world;
    private GameView view;
    private PlayerController controller;
    private JFrame frame;
    private SoundManager soundManager;
    private PauseMenuPanel pausePanel;

    // timer drives camera tracking and game-end detection every frame
    private Timer gameTimer;
    private boolean gameEndShown = false;

    private int levelNumber = 1;
    private boolean paused = false;

    // ArrayList of level classes - used to create each level by index
    private static final ArrayList<Class<? extends GameWorld>> LEVELS = new ArrayList<>(
        Arrays.asList(Level1.class, Level2.class, Level3.class, Level4.class)
    );

    public Game() {
        soundManager = new SoundManager();

        frame = new JFrame("City Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);

        showMainMenu();
        frame.setVisible(true);
    }

    // creates a level instance from the LEVELS list by index (0-based)
    private GameWorld createLevel(int index) {
        try {
            return LEVELS.get(index).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Could not create level " + (index + 1));
            return new Level1();
        }
    }

    public void showMainMenu() {
        stopGameTimer();
        if (world != null) world.stop();
        frame.getGlassPane().setVisible(false);
        frame.getContentPane().removeAll();
        frame.add(new MainMenuPanel(this, soundManager));
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    public void showInstructions() {
        frame.getContentPane().removeAll();
        frame.add(new InstructionsPanel(this));
        frame.revalidate();
        frame.repaint();
    }

    // starts a fresh game from level 1
    public void startGame() {
        levelNumber = 1;
        world = createLevel(0);
        setupLevel();
    }

    // loads a saved game via JFileChooser
    public void loadGame() {
        int[] saved = SaveManager.loadGame();
        if (saved == null) return; // cancelled
        levelNumber = Math.max(1, Math.min(saved[0], LEVELS.size()));
        world = createLevel(levelNumber - 1);
        world.setLives(saved[2]);
        world.setScore(saved[1]);
        setupLevel();
    }

    // saves current game state via JFileChooser
    public void saveGame() {
        if (world != null) {
            SaveManager.saveGame(levelNumber, world.getScore(), world.getLives());
        }
    }

    private void setupLevel() {
        world.setSoundManager(soundManager);
        gameEndShown = false;
        soundManager.playLevelMusic(levelNumber);

        view = new GameView(world, 800, 600);
        view.setCentre(world.getPlayer().getPosition());

        controller = new PlayerController(world.getPlayer(), soundManager);
        view.addKeyListener(controller);

        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) togglePause();
            }
        });

        frame.getContentPane().removeAll();
        frame.add(view);

        pausePanel = new PauseMenuPanel(this);
        frame.setGlassPane(pausePanel);
        pausePanel.setVisible(false);

        frame.revalidate();
        frame.repaint();
        frame.pack();

        view.requestFocus();
        world.getPlayer().addCollisionListener(new GateCollision(this));
        world.start();
        startGameTimer();
    }

    private void startGameTimer() {
        stopGameTimer();
        gameTimer = new Timer(16, e -> {
            if (world == null || world.getPlayer() == null) return;
            view.setCentre(world.getPlayer().getPosition());
            if (!gameEndShown && world.isGameOver()) {
                gameEndShown = true;
                SaveManager.saveHighScore(world.getScore());
                showEndScreen(false, world.getScore());
            }
        });
        gameTimer.start();
    }

    private void stopGameTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
            gameTimer = null;
        }
    }

    public void goToNextLevel() {
        int currentLives = world.getLives();
        int currentScore = world.getScore();
        stopGameTimer();
        world.stop();

        levelNumber++;

        if (levelNumber > LEVELS.size()) {
            soundManager.stopMusic();
            SaveManager.saveHighScore(currentScore);
            showEndScreen(true, currentScore);
            return;
        }

        world = createLevel(levelNumber - 1);
        world.setLives(currentLives);
        world.setScore(currentScore);
        setupLevel();
    }

    public void showEndScreen(boolean won, int score) {
        soundManager.stopMusic();
        GameOverPanel endPanel = new GameOverPanel(this, won, score);
        frame.setGlassPane(endPanel);
        endPanel.setVisible(true);
    }

    public void togglePause() {
        if (paused) resumeGame();
        else pauseGame();
    }

    public void pauseGame() {
        paused = true;
        world.stop();
        stopGameTimer();
        pausePanel.setVisible(true);
    }

    public void resumeGame() {
        paused = false;
        pausePanel.setVisible(false);
        view.requestFocus();
        world.start();
        startGameTimer();
    }

    public void quitToMenu() {
        paused = false;
        pausePanel.setVisible(false);
        levelNumber = 1;
        showMainMenu();
    }

    public SoundManager getSoundManager() { return soundManager; }
    public GameWorld getWorld() { return world; }

    public static void main(String[] args) {
        new Game();
    }
}
