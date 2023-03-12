package Vue;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class VueController extends JPanel {
    private final Map map;
    private final int hauteur = Map.taille;
    public static final int largeur = 300;

    public VueController(Map map){

        this.map=map;
        setPreferredSize(new Dimension(largeur,hauteur));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(BanqueImage.imgBackCommande,0,0,largeur,hauteur,null);

    }
}
