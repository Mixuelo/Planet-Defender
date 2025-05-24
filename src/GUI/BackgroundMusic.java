package GUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic
{
    private Clip clip;

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

    public void stopMusic()
    {
        if (clip != null && clip.isRunning()) clip.stop();
    }
}
