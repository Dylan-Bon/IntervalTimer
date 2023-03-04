import javax.sound.sampled.*;
import java.io.File;

public class Sound extends Thread {
    Clip clip;
    boolean playing;

    public void run() {
        try {
            File file = new File("src/sounds/beep.wav");

            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

            setPlaying(true);

            Thread.sleep(100);
            clip.close();

        } catch (Exception e) {
            System.out.println(e+"\nThere was a problem when playing the sound.");
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

}
