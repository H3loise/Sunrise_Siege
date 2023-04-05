package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class TimeChanger extends Thread {
    private Map m;
    public TimeChanger(Map m) {
        this.m = m;
    }

    /**
     * Permet de jouer une musique et renvoie la dernière musique jouer
     * @param s
     * @return clip
     */
    public Clip playMusic(String s) {
        try {
            File musicFile = new File(s);
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

    /**
     * Permet de changer entre le jour et la nuit
     * Permet de jouer les musiques en fonction de si c'est le jour ou la nuit
     * Permet aussi de récuperer à quelle moment du cycle on est
     */
    @Override
    public void run() {
        System.out.println(m.getNexus().toString());
        Clip clip = null;
        m.setStartTime(System.currentTimeMillis());
        while (!m.testLoose()) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
           /* if (this.m.getDay()) {
                clip = playMusic("src/Music/NightSong.wav");

            } else {
                clip = playMusic("src/Music/DaySong.wav");
            }*/
            m.setDay(!m.getDay());
            m.update();

            m.setStartTime(System.currentTimeMillis());

            try {
                sleep(m.getDelaiJourNuit());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
