package Vue;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class VueInfo extends JPanel {

    private final Map map;
    private final int hauteur = Map.taille;
    private final int longueur = 200;

    public VueInfo(Map map){
        this.map = map;
        setPreferredSize(new Dimension(longueur,hauteur));
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN,50));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,longueur,hauteur-100);
    }
}
