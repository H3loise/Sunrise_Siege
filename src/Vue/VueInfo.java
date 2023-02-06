package Vue;

import Model.Map;
import Model.Personnages.Guerrier;
import Vue.Infos.VueBouttons;
import Vue.Infos.VueInfosPersos;

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
    private final BufferedImage image = ImageIO.read(new File("src/parchemin.png"));

    public VueInfo(Map map) throws IOException {
        this.map = map;
        this.setPreferredSize(new Dimension(longueur,hauteur));
        this.setLayout(new GridLayout(3,1,0,20));
        JPanel haut = new JPanel();
        JPanel milieu = new VueBouttons();
        JPanel bas = new JPanel();
        //haut.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        this.add(haut);
        this.add(milieu);
        //this.setBorder(BorderFactory.createLineBorder(Color.GREEN,50));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0,-40,longueur,hauteur,null);
        new VueInfosPersos(new Guerrier(0,0)).paint(g);
    }
}
