package Vue;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class VueRessources extends JPanel {
    private Map map;
    private final int hauteur = 40;
    private final int longueur = 300;
    public VueRessources(Map map){
        this.map = map;
        setPreferredSize(new Dimension(hauteur,longueur));

    }

    @Override
    public void paint(Graphics g) {
        super.repaint();
        g.drawString("Nombre de nourriture : " + map.getFood(),40,300);
    }
}
