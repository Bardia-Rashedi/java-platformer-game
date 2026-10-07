package game;

import city.cs.engine.SoundClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SoundManager {

    private SoundClip backgroundMusic;

    // all sound effects are preloaded once and reused
    private SoundClip hurtSound;
    private SoundClip eatingSound;
    private SoundClip portalSound;
    private SoundClip jumpingSound;

    private boolean muted = false;
    private double volume = 1.0;

    public SoundManager() {
        // preload each sound effect once so they don't reload every time
        try {
            hurtSound = new SoundClip("data/Hurt.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Could not load Hurt.wav");
        }
        try {
            eatingSound = new SoundClip("data/Eating.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Could not load Eating.wav");
        }
        try {
            portalSound = new SoundClip("data/Portal.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Could not load Portal.wav");
        }
        try {
            jumpingSound = new SoundClip("data/Jumping.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Could not load Jumping.wav");
        }
    }

    // played when character touches fire
    public void playHurt() {
        if (!muted && hurtSound != null) {
            hurtSound.setVolume(volume);
            hurtSound.play();
        }
    }

    // played when character picks up a carrot
    public void playEating() {
        if (!muted && eatingSound != null) {
            eatingSound.setVolume(volume);
            eatingSound.play();
        }
    }

    // played when character reaches the portal/gate
    public void playPortal() {
        if (!muted && portalSound != null) {
            portalSound.setVolume(volume);
            portalSound.play();
        }
    }

    // played when the character jumps
    public void playJumping() {
        if (!muted && jumpingSound != null) {
            jumpingSound.setVolume(volume);
            jumpingSound.play();
        }
    }

    // mute stops all sound, unmute resumes
    public void setMuted(boolean m) {
        muted = m;
        if (backgroundMusic != null) {
            if (muted) {
                backgroundMusic.stop();
            } else {
                backgroundMusic.setVolume(volume);
                backgroundMusic.loop();
            }
        }
    }

    public boolean isMuted() {
        return muted;
    }

    // volume: 0.0 (silent) to 2.0 (loud), 1.0 is normal
    public void setVolume(double v) {
        volume = v;
        if (backgroundMusic != null && !muted) {
            backgroundMusic.setVolume(volume);
        }
    }

    public double getVolume() {
        return volume;
    }

    // plays looping background music for the given level number
    // add music1.wav, music2.wav, music3.wav, music4.wav to data/ to enable
    public void playLevelMusic(int level) {
        stopMusic();
        String[] tracks = {
            "data/music1.wav",
            "data/music2.wav",
            "data/music3.wav",
            "data/music4.wav"
        };
        int index = level - 1;
        if (index < 0 || index >= tracks.length) index = 0;
        try {
            backgroundMusic = new SoundClip(tracks[index]);
            if (!muted) {
                backgroundMusic.loop();          // start first
                backgroundMusic.setVolume(volume); // then apply volume
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("No music file for level " + level + " - skipping");
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }
}
