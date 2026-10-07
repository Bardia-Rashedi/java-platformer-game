package game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

// handles saving/loading game state and tracking the high score
public class SaveManager {

    private static final String HIGH_SCORE_FILE = "data/highscore.txt";

    // opens a JFileChooser and saves level, score, lives to a .txt file
    public static void saveGame(int level, int score, int lives) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Game");
        chooser.setFileFilter(new FileNameExtensionFilter("Game Save (*.txt)", "txt"));
        chooser.setSelectedFile(new File("savegame.txt"));

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("level=" + level);
                writer.println("score=" + score);
                writer.println("lives=" + lives);
                JOptionPane.showMessageDialog(null, "Game saved!", "Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                System.out.println("Could not save game: " + e.getMessage());
            }
        }
    }

    // opens a JFileChooser and loads level, score, lives from a .txt file
    // returns int[] { level, score, lives } or null if cancelled/failed
    public static int[] loadGame() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load Game");
        chooser.setFileFilter(new FileNameExtensionFilter("Game Save (*.txt)", "txt"));

        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int level = 1, score = 0, lives = 3;
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        switch (parts[0].trim()) {
                            case "level": level = Integer.parseInt(parts[1].trim()); break;
                            case "score": score = Integer.parseInt(parts[1].trim()); break;
                            case "lives": lives = Integer.parseInt(parts[1].trim()); break;
                        }
                    }
                }
                return new int[]{level, score, lives};
            } catch (IOException | NumberFormatException e) {
                System.out.println("Could not load game: " + e.getMessage());
            }
        }
        return null;
    }

    // saves high score to file if the new score beats the existing one
    public static void saveHighScore(int score) {
        if (score > loadHighScore()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(HIGH_SCORE_FILE))) {
                writer.println(score);
            } catch (IOException e) {
                System.out.println("Could not save high score");
            }
        }
    }

    // reads the stored high score (0 if no file exists yet)
    public static int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            // no high score yet
        }
        return 0;
    }
}
