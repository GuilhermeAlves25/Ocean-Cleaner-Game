package meujogo.controle;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Som {

    private Clip clip;

    public Som(String caminho) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(caminho));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Tocar o som uma vez
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    // Tocar em loop contínuo (para música de fundo)
    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Parar o som
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
