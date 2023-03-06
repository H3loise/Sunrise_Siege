package Vue;

import Model.Map;
import Model.Personnages.Guerrier;
import Vue.Infos.VueBouttons;
import Vue.Infos.VueInfosPersos;
import Vue.Infos.VueMessages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueInfo extends JPanel {

    private final Map map;
    private final int hauteur = Map.taille;
    private final int longueur = 300;
    private final BufferedImage image = ImageIO.read(new File("src/parchemin1.png"));
    private JPanel lowerVue = new VueMessages();

    public VueInfo(Map map) throws IOException {
        this.map = map;
        this.setPreferredSize(new Dimension(longueur,hauteur));
        this.setLayout(new GridLayout(3,1,0,20));
        JPanel haut = new JPanel();
        JPanel milieu = new VueBouttons();
        JPanel bas = new JPanel();
        this.add(haut);
        this.add(milieu);
        //JPanel lowerJpanel = new VueMessages();
        this.add(lowerVue);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        //new VueMessages().paint(g);
        g.drawImage(image,0,-40,longueur,hauteur,null);
        lowerVue.paint(g);
        //new VueInfosPersos(new Guerrier(0,0)).paint(g);

    }
}
