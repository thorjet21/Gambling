import javax.sound.sampled.*;
import java.io.File;

public class Music {
    public static void main(String[] args) {
        try {
            File music = new File("src/jazzMusic.wav");
            AudioInputStream musicStream = AudioSystem.getAudioInputStream(music);

            Clip clip = AudioSystem.getClip();
            clip.open(musicStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            Thread.sleep(Long.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
