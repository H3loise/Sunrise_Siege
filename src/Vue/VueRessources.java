package Vue;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class VueRessources extends JPanel {
    private final Map map;
    private final int hauteur = 30;
    private final int longueur = Map.taille;

    public VueRessources(Map map){
        this.map = map;
        setPreferredSize(new Dimension(longueur,hauteur));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(0,0,longueur,hauteur);
        g.setColor(Color.black);
        g.drawString("Nombre de nourriture : " + map.getFood(),10,20);
        g.setColor(Color.black);
        g.drawRect(0,0,longueur,hauteur);

    }
}