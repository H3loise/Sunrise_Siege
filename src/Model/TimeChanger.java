package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
public class TimeChanger extends Thread{
    Map m;
    private final int delai = 50000;
    public TimeChanger(Map m){
        this.m=m;
    }
    public Clip playMusic(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(m.getNexus().toString());
        Clip clip = null;
        while (!m.testLoose()) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            if (this.m.getDay()) {
                clip = playMusic("src/Music/NightSong.wav");

            } else {
                clip = playMusic("src/Music/DaySong.wav");
            }

            m.setDay(!m.getDay());
            m.update();

            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

