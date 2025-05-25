package GUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe responsável pela música de fundo do jogo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (25/05/2025)
 */
public class BackgroundMusic
{
    private Clip clip;

    /**
     * Inicia a música (em loop)
     * @param filePath {@code String}
     */
    public void playMusic(String filePath)
    {
        try
        {
            File musicPath = new File(filePath);

            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Pára a música.
     */
    public void stopMusic()
    {
        if (clip != null && clip.isRunning()) clip.stop();
    }
}
